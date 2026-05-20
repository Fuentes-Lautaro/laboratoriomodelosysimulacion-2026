package com.laboratorio.dominio;

import java.util.List;

public interface Event {

    double getClock();

    int getOrder();

    void planificate(FutureEventList fel, Server server);

    Entity getEntity();

    List<Distribution> getDistributions();
}
