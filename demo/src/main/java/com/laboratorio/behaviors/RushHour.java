package com.laboratorio.behaviors;

import java.util.List;

import com.laboratorio.dominio.Behavior;
import com.laboratorio.dominio.Distribution;

/**
 * A partir de una lista de distribuciones, selecciona cual usar dependiendo la hora de la simulacion
 * @author eldem
 */
public class RushHour implements Behavior {

    @Override
    public double behavior(List<Distribution> distributions, double clock) {
        double r =clock % 1440;

        if(r >= 9*60 && r <= 13*60 || r >= 20*60 && r <= 23*60){
            return distributions.get(1).sample();
        }
        return distributions.get(0).sample();
    }
    
}