package com.laboratorio.dominio;

public interface Server {

    long getId();
    Entity getEntity(); 
    void setEntity(Entity e);
    Queue getQueue();
    void setQueue(Queue q);

    void free();
    boolean isBusy();
}
