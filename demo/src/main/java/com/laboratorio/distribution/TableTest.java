package com.laboratorio.distribution;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

public class TableTest implements Distribution {

    @Override
    public double sample() {
        double r = Randomizer.nroRandom();
        if (r < 0.35) return 40d;
        if (r < 0.8) return 50d;
        return 60d;
    }
}
