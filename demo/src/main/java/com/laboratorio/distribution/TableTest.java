package com.laboratorio.distribution;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

public class TableTest implements Distribution {

    @Override
    public double sample() {
        double r = Randomizer.nroRandom();
        if (r < 0.35) return 2d;
        if (r < 0.8) return 3d;
        return 4d;
    }
}
