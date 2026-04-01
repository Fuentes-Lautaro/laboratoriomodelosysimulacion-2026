package com.laboratorio;
public class TiempoEntreArribos {
    public static int tiempoEntreArribos(){
        double tea = nroRandom();
        if (tea < 0.34) return 1;
        if (tea < 0.82) return 2;
        return 3;
    }
}
