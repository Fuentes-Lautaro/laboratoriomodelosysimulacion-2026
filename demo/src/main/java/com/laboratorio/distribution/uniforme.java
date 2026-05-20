package com.laboratorio.distribution;

import com.laboratorio.dominio.Distribution;

public class Uniforme implements Distribution {

    @Override
    public double sample() {
        return 10 + (20 - 10) * Math.random();
    }

}
