package com.laboratorio.scenario;

import java.util.List;
import com.laboratorio.collectors.CollectorSizeQueue;  
import com.laboratorio.collectors.CollectorTimeLeisure;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
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
    private final Distribution arrivalDistribution;
    private final Distribution EoSDistribution;
    private final CollectorTimeOnSystem collectorToS;
    private final CollectorTimeWait collectorWait;
    private final CollectorSizeQueue collectorSQ;
    private final CollectorTimeLeisure collectorTL;
    private final ServerSelectionPolicy selectionPolicy;

    public Arrival(Double clock, Entity entity, Distribution arrivalDistribution, Distribution EoSDistribution, CollectorTimeOnSystem collectorToS, CollectorTimeWait collectorWait, CollectorSizeQueue collectorSQ, CollectorTimeLeisure collectorTL, ServerSelectionPolicy selectionPolicy) {
        this.clock = clock;
        this.order = 10;
        this.entity = entity;
        this.arrivalDistribution = arrivalDistribution;
        this.EoSDistribution = EoSDistribution;
        this.collectorToS = collectorToS;
        this.collectorWait = collectorWait;
        this.collectorSQ = collectorSQ;
        this.collectorTL = collectorTL;
        this.selectionPolicy = selectionPolicy;
    }

    @Override
    public double getClock(){
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
    public Distribution getDistribution() {
        return this.arrivalDistribution;
    }

    @Override
    public void planificate(FutureEventList fel, List<Server> servers) {

        this.entity.setTimeArrival(this.clock);
        this.collectorToS.collectArrival();

        Server server = this.selectionPolicy.selectServer(servers);
        this.entity.setServer(server);
        double tr = this.clock + this.EoSDistribution.sample();

        if (server.isBusy()){

            server.getQueue().enqueue(this.entity);

            this.collectorSQ.collect(server.getQueue().size());

        }else{
            
            this.collectorTL.collect(this.clock - server.getLeisureTime());

            server.setEntity(this.entity);

            fel.insert(new EndOfService(tr, this.entity, this.EoSDistribution, this.collectorToS, this.collectorWait));

        }

        System.out.println("Server " + server.getId());
        fel.insert(new Arrival(this.clock + this.arrivalDistribution.sample(), new Entity(), this.arrivalDistribution, this.EoSDistribution, this.collectorToS, this.collectorWait, this.collectorSQ, this.collectorTL, this.selectionPolicy));
        
    }
}
