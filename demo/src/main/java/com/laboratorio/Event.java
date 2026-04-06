package com.laboratorio;

public interface Event {

    double clock();

    int order();

    void planificate(FutureEventList fel, Server server);
}
