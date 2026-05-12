package com.laboratorio.distribution;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

public class TableTest implements Distribution {

    @Override
    public double sample() {
        double r = Randomizer.nroRandom();
        if (r < 0.35) return 100d;
        if (r < 0.8) return 150d;
        return 200d;
    }
}
