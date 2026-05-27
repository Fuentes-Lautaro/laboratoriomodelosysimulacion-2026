package com.laboratorio.dominio;

public interface Server {

    long getId();

    Entity getEntity(); 

    void setEntity(Entity e);

    Queue getQueue();

    void setLeisureTime(double leisureTime);

    double getLeisureTime();
    
    void setQueue(Queue q);

    void free();

    boolean isBusy();

    void setDurability(double wearStrip);

    double getDurability();
}
