package com.laboratorio.dominio;

import com.laboratorio.collectors.CollectorServerStats;

public interface Server {

    long getId();

    Entity getEntity(); 

    void setEntity(Entity e);

    Queue getQueue();

    double getLastEntityClock();

    void setLastEntityClock(double lastEntityClock);
    
    void setQueue(Queue q);

    CollectorServerStats getCollectorServerStats();

    void free();

    boolean isBusy();

    void setDurability(double wearStrip);

    double getDurability();
}