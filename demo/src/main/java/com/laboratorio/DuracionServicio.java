package com.laboratorio;

public class DuracionServicio {

    public static int tiempoServicio(){
        double inspeccion = Realeatorizador.nroRandom();
        if (inspeccion < 0.38) return 8;
        if (inspeccion < 0.7) return 10;
        if (inspeccion < 0.8) return 13;
        return 15;
    }
}
