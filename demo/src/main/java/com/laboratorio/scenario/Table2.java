package com.laboratorio.scenario;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

public class Table2 implements  Distribution{

    @Override
    public double sample(double cumulativeProbability) {
        double inspeccion = Randomizer.nroRandom();
        if (inspeccion < 0.38) return 8d;
        if (inspeccion < 0.7) return 10d;
        if (inspeccion < 0.8) return 13d;
        return 15d;
    }
    
}
