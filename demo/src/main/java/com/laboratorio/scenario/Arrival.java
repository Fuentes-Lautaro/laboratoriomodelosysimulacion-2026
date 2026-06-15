package com.laboratorio.scenario;

import java.util.List;

import com.laboratorio.collectors.CollectorSizeQueue;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.collectors.CollectorServerStats;
import com.laboratorio.dominio.Behavior;
import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;
import com.laboratorio.dominio.ServerSelectionPolicy;

public class Arrival implements Event {
    private final double clock;
    private final int order;
    private final Entity entity;
    private final List<Distribution> arrivalDistributions;
    private final List<Distribution> eoSDistributions;
    private final Distribution durabilityDistribution;
    private final CollectorTimeOnSystem collectorToS;
    private final CollectorTimeWait collectorWait;
    private final CollectorSizeQueue collectorSQ;
    private final List<CollectorServerStats> collectorsST;
    private final ServerSelectionPolicy selectionPolicy;
    private final Behavior behavior;
    private Behavior eoSBehavior;

    public Arrival(Double clock,
            Entity entity, List<Distribution> arrivalDistributions, List<Distribution> eoSDistributions,
            Distribution durabilityDistribution, CollectorTimeOnSystem collectorToS, CollectorTimeWait collectorWait,
            CollectorSizeQueue collectorSQ, List<CollectorServerStats> collectorsST, 
            ServerSelectionPolicy selectionPolicy, 
            Behavior arrivalBehavior, Behavior eoSBehavior) {
        this.clock = clock;
        this.order = 10;
        this.entity = entity;
        this.arrivalDistributions = arrivalDistributions;
        this.eoSDistributions = eoSDistributions;
        this.durabilityDistribution = durabilityDistribution;
        this.collectorToS = collectorToS;
        this.collectorWait = collectorWait;
        this.collectorSQ = collectorSQ;
        this.collectorsST = collectorsST;
        this.selectionPolicy = selectionPolicy;
        this.behavior = arrivalBehavior;
        this.eoSBehavior = eoSBehavior;
    }

    @Override
    public double getClock() {
        return clock;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public List<Distribution> getDistributions() {
        return this.arrivalDistributions;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers){

        this.entity.setTimeArrival(this.clock);
        this.collectorToS.collectArrival();

        Server server = this.selectionPolicy.selectServer(servers);
        this.entity.setServer(server);

        if (server.isBusy()) {

            server.getQueue().enqueue(this.entity);

            collectorSQ.collect(server.getQueue().size());

        } else {

            collectorsST.get(server.getId()-1).collect(clock - server.getLastEntityClock());

            server.setEntity(this.entity);

            double deltaTime = this.eoSBehavior.behavior(this.eoSDistributions, this.clock);
            fel.insert(new EndOfService(this.clock + deltaTime, this.entity, this.eoSDistributions, this.collectorToS,
                    this.collectorWait, this.eoSBehavior));

        }
        double deltaTime = this.behavior.behavior(this.arrivalDistributions, this.clock);
        fel.insert(new Arrival(this.clock + deltaTime, new Entity(), this.arrivalDistributions, this.eoSDistributions, 
                this.durabilityDistribution, this.collectorToS, this.collectorWait, this.collectorSQ, this.collectorsST,
                this.selectionPolicy, this.behavior, this.eoSBehavior));

        server.setDurability(this.durabilityDistribution.sample());
    }
}