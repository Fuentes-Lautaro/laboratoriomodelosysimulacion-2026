package com.laboratorio.distribution;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

public class Table2 implements  Distribution{

    @Override
    public double sample() {
        double r = Randomizer.nroRandom();
        if (r < 0.38) return 8d;
        if (r < 0.7) return 10d;
        if (r < 0.8) return 13d;
        return 15d;
    }
    
}
