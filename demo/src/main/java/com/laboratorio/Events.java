package com.laboratorio;

import java.util.concurrent.Future;

public interface Events {

    double clock();

    int order();

    void planificate(FutureEventList fel, Server server);
}
