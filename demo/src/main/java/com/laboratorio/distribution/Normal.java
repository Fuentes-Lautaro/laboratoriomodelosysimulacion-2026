package com.laboratorio.distribution;
import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

/**
 * Crea variables aleatorias con una distribucion normal a partir de una media y una desviacion estandar.
 * @param mean
 * @param stdDev
 * @author eldem
 */
public class Normal implements Distribution{

    private double mean;
    private double stdDev;

    public Normal(double mean, double stdDev) {
        this.mean = mean;
        this.stdDev = stdDev;
    }
    
    @Override
    public double sample() {
        double z = 0;
        for (int i = 0; i < 48; i++)
            z += Randomizer.nroRandom();

        z = .5 * (z - 24);

        return z * stdDev + mean;
    }
} 