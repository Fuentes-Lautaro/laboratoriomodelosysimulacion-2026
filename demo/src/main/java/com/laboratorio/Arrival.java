package com.laboratorio;

public class Arrival implements Event {
    private double clock;
    private int order;

    public Arrival(Double clock) {
        this.clock = clock;
        this.order = 10;
    }

    public double clock()[
        return clock;
    ]

    public int order() {
        return order;
    }

    public void planificate(FutureEventList fel, Server server){
        
    }
}
