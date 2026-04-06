package com.laboratorio;

public interface Event {

    double getClock();

    int getOrder();

    void planificate(FutureEventList fel, Server server);
}
