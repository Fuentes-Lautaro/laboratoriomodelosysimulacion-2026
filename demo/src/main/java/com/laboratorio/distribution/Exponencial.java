package com.laboratorio.distribution;

public class Exponencial {
    public static double generate(double lambda) {
        double u = Math.random(); 
        return -Math.log(1 - u) / lambda;
    }
}
