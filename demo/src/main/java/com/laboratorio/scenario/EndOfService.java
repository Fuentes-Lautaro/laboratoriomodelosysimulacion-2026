package com.laboratorio.scenario;

import java.util.List;

import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.dominio.Behavior;
import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;

public class EndOfService implements Event {
    private final double clock;
    private final int order;
    private final Entity entity;
    private final List<Distribution> distributions;
    private final CollectorTimeOnSystem collectorToS;
    private final CollectorTimeWait collectorWait;
    private final Behavior behavior;

    public EndOfService(Double clock, Entity e, List<Distribution> distributions, 
                        CollectorTimeOnSystem collectorToS, CollectorTimeWait collectorWait, 
                        Behavior behavior) 
                        {
        this.clock = clock;
        this.order = 0;
        this.entity = e;
        this.distributions = distributions;
        this.collectorToS = collectorToS;
        this.collectorWait = collectorWait;
        this.behavior = behavior;
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
    public List<Distribution> getDistributions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    @Override
    public void planificate(FutureEventList fel, List<Server> servers){

        Entity e = null;
        Server server = this.entity.getServer();
        
        if (server.getQueue().size() > 0){ 

            e = server.getQueue().next();

            server.setEntity(e);

            this.collectorWait.collect(this.clock - e.getTimeArrival());

            double deltaTime = this.behavior.behavior(this.distributions, this.clock);
            fel.insert(new EndOfService(this.clock + deltaTime, e, this.distributions, this.collectorToS, 
                                        this.collectorWait, this.behavior));
        }else{

            server.free();

            server.setLeisureTime(this.clock);
            
        }
        
        this.collectorToS.collect(this.clock - this.entity.getTimeArrival());
    }
}