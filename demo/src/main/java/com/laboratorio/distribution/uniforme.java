package com.laboratorio.distribution;

import com.laboratorio.dominio.Distribution;

/**
 * Crea variables aleatorias con una distribucion Uniforme a partir de los valores del intervalo (a,b)
 * @param a
 * @param b
 * @author eldem
 */
public class Uniforme implements Distribution{
    private double a;
    private double b;

    public Uniforme(double a, double b){
        this.a = a;
        this.b = b;
    }

    @Override
    public double sample() {
        return this.a + (this.b - this.a) * Math.random();
    }

}