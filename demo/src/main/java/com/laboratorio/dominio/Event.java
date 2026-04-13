package com.laboratorio.dominio;

public interface Event {

    double getClock();

    int getOrder();

    void planificate(FutureEventList fel, Server server);

    Entity getEntity();

    Distribution getDistribution();
}
