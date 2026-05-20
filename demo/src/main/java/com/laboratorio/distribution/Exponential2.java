package com.laboratorio.distribution;

import com.laboratorio.dominio.Distribution;

public class Exponential2 implements Distribution {
    
    private final Exponencial exp1;
    private final Exponencial exp2;
    private double clock;

    public Exponential2(double mu1, double mu2, double clock) {
        this.exp1 = new Exponencial(mu1);
        this.exp2 = new Exponencial(mu2);
        this.clock = clock;
    }

    public void setClock(double clock) {
        this.clock = clock;
    }
    
    private boolean rushHour() {
        double r =this.clock % 1440;
        
        return r >= 9*60 && r <= 13*60 || r >= 20*60 && r <= 23*60;
    }

    @Override
    public double sample() {
        if (this.rushHour()) {
            return this.exp1.sample();
        } else {
            return this.exp2.sample();
        }
    }   
}
