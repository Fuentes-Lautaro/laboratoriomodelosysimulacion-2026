package com.laboratorio.scenario;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;

public class Arrival implements Event {
    private final double clock;
    private final int order;
    private final Entity entity;
    private final Distribution arrivalDistribution;
    private final Distribution EoSDistribution;
    private final CollectorTimeOnSystem collectorToS;
    private final CollectorTimeWait collectorWait;
    private final CollectorSizeQueue collectorSQ;

    public Arrival(Double clock, Entity entity, Distribution arrivalDistribution, Distribution EoSDistribution, CollectorTimeOnSystem collectorToS, CollectorTimeWait collectorWait, CollectorSizeQueue collectorSQ) {
        this.clock = clock;
        this.order = 10;
        this.entity = entity;
        this.arrivalDistribution = arrivalDistribution;
        this.EoSDistribution = EoSDistribution;
        this.collectorToS = collectorToS;
        this.collectorWait = collectorWait;
        this.collectorSQ = collectorSQ;
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
    public void planificate(FutureEventList fel, Server server){
        this.entity.setTimeArrival(this.clock);
        this.collectorToS.collectArrival();
        if (server.isBusy()){
            server.getQueue().enqueue(this.entity);
            collectorSQ.collect(server.getQueue().size());
        }else{
            server.setEntity(this.entity);
            collectorSQ.collect(server.getQueue().size());
            fel.insert(new EndOfService(this.clock + this.EoSDistribution.sample(), this.entity, this.EoSDistribution, this.collectorToS, this.collectorWait)); //INSERTO EL EVENTO SALIDA DEL ELEMENTO ACTUAL
        }

        fel.insert(new Arrival(this.clock + this.arrivalDistribution.sample(), new Entity(), this.arrivalDistribution, this.EoSDistribution, this.collectorToS, this.collectorWait, this.collectorSQ)); //INSERTO EL NUEVO EVENTO DE ARRIBO

        //COLECCIONO ESTADISTICAS
        //TERMINA EL PLANIFICATE
    }
}
