package com.laboratorio.distribution;
import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

public class Exponencial implements Distribution {

    private double mu;
    
    public Exponencial(double mu) {
        this.mu = mu;
    }
   
    @Override
    public double sample() {
        return (-this.mu) * Math.log(1 - Randomizer.nroRandom());
    }

}
