package com.laboratorio.dominio;
import java.util.Random;

public class Randomizer {
    private static Random random= new Random();
    
    public static double nroRandom() {
        return random.nextDouble();
    }

    public static double exponencial(double mu) {
    return -mu * Math.log(1.0 - nroRandom());
    }

    public static double uniforme(double a, double b) {
    return a + (b - a) * nroRandom();
    }

    public static double normal(double mu, double sigma){
    return mu + (sigma * random.nextGaussian());
    }

    public static int empiric(double[] probabilities) {
        double r = nroRandom();
        double cumulative = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulative += probabilities[i];
            if (r < cumulative) {
                return i;
            }
        }
        return probabilities.length - 1; 
    }
} 