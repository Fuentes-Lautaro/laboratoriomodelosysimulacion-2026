package com.laboratorio.dominio;
import java.util.List;

public interface Event {

    double getClock();

    int getOrder();

    void planificate(FutureEventList fel, List<Server> servers);

    Entity getEntity();

    Distribution getDistribution();
}
