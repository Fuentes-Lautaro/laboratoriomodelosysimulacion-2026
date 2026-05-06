package com.laboratorio.scenario;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;

public class EndOfService implements Event {
    private final double clock;
    private final int order;
    private final Entity entity;
    private final Distribution distribution;
    private final CollectorTimeOnSystem collectorToS;
    private final CollectorTimeWait collectorWait;

    public EndOfService(Double clock, Entity e, Distribution distribution, CollectorTimeOnSystem collectorToS, CollectorTimeWait collectorWait) {
        this.clock = clock;
        this.order = 0;
        this.entity = e;
        this.distribution = distribution;
        this.collectorToS = collectorToS;
        this.collectorWait = collectorWait;
    }

    @Override
    public double getClock(){
        return this.clock;
    }

    @Override
    public int getOrder(){
        return this.order;
    }

     @Override
    public Entity getEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Distribution getDistribution() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    @Override
    public void planificate(FutureEventList fel, Server server){

        Entity e = null;
        
        if (server.getQueue().size() > 0){ 

            e = server.getQueue().next();
            server.setEntity(e);
            this.collectorWait.collect(this.clock - e.getTimeArrival());
            fel.insert(new EndOfService(this.clock+this.distribution.sample(), e, this.distribution, this.collectorToS, this.collectorWait));

        }else{

            server.free();
            
        }
        
        this.collectorToS.collect(this.clock - this.entity.getTimeArrival());
    }
}