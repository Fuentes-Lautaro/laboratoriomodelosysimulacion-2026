package com.laboratorio.scenario;

import com.laboratorio.collectors.CollectorServerStats;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Queue;
import com.laboratorio.dominio.Server;

public class Airstrip implements Server {
    private long id;
    private Entity entity;
    private Queue queue;
    private double lastEntityClock;
    private double durability;
    private final CollectorServerStats CollectorST;

    public Airstrip(int id) {
        this.id = id;
        this.lastEntityClock = 0.0;
        this.CollectorST = new CollectorServerStats();
        this.durability = 3000;
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public Queue getQueue() {
        return this.queue;
    }

    @Override
    public void setEntity(Entity e) {
        this.entity =e;
    }

    @Override
    public void setDurability(double wearStrip){
        this.durability -= wearStrip;
    }

    @Override
    public double getDurability(){
        return this.durability;
    }
    
    @Override
    public void setQueue(Queue q) {
        this.queue = q;
    }

    @Override
    public double getLastEntityClock(){
        return this.lastEntityClock;
    }

    @Override
    public void setLastEntityClock(double lastEntityClock){
        this.lastEntityClock = lastEntityClock;
    }

    @Override
    public CollectorServerStats getCollectorServerStats(){
        return this.CollectorST;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public boolean isBusy() {
        return this.entity != null;
    }

    @Override
    public void free() {
        this.entity = null;
    }
}