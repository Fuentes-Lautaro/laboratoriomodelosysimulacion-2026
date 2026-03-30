package com.laboratorio;

public class EndOfService implements Event {
    private double clock;
    private int order;

    public EndOfService(Double clock) {
        this.clock = clock;
        this.order = 0;
    }

    public int order() {
        return order;
    }
    
    public void planificate(FutureEventList fel, Server server){
        
    }

}
