package com.laboratorio.scenario;

import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Queue;
import com.laboratorio.dominio.Server;

public class Airstrip implements Server {
    private long id;
    private Entity entity;
    private Queue queue;
    private double leisureTime;

    public Airstrip(int id, Queue queue) {
        this.id = id;
        this.queue = queue;
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
    public void setQueue(Queue q) {
        this.queue = q;
    }

    public double getLeisureTime() {
        return this.leisureTime;
    }

    public void setLeisureTime(double leisureTime) {
        this.leisureTime = leisureTime;
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