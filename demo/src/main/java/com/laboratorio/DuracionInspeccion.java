package com.laboratorio;

public class DuracionInspeccion {
    
    public static int tiempoSalida(){
        double inspeccion = nroRandom();
        if (inspeccion < 0.05) return 1;
        if (inspeccion < 0.22) return 2;
        if (inspeccion < 0.45) return 3;
        if (inspeccion < 0.7) return 4;
        if (inspeccion < 0.88) return 5;
        return 6;
    }
}
