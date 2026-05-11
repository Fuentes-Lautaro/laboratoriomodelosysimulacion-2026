package com.laboratorio.distribution;

public abstract class distribucion {
    public double media;
    public double desviacionEstandar;

    public distribucion() {
    }

    public distribucion(double media, double desviacionEstandar) {
        this.media = media;
        this.desviacionEstandar = desviacionEstandar;
    }

    abstract public double getTime(double event);
    abstract public double getProbability(double acumulative);
    abstract public distribucion verifTime(double clock);
}
