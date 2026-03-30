package com.laboratorio;

public class Arrival implements Events {
    private double clock;
    private int order;

    public Arrival(Double clock) {
        this.clock = clock;
        this.order = 10;
    }

    public int order() {
        return order;
    }

}
