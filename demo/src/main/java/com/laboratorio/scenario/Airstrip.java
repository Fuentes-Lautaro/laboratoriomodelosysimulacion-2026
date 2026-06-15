package com.laboratorio.scenario;

import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Queue;
import com.laboratorio.dominio.Server;

public class Airstrip implements Server {
    private long id;
    private Entity entity;
    private Queue queue;
    private double lastEntityClock;
    private double leisureTime;
    private double durability = 3000;

    public Airstrip(int id) {
        this.id = id;
        this.lastEntityClock = 0.0;
        this.leisureTime = 0.0;
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
    public double getLeisureTime() {
        return this.leisureTime;
    }
    @Override
    public void setLeisureTime(double leisureTime) {
        this.leisureTime += leisureTime - this.lastEntityClock;
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