package com.laboratorio;
public class TiempoEntreArribos {
    public static double TiempoEntreArribos(){
        double tea = nrmRandom();
        if (tea < 0.34) return 1;
        if (tea < 0.82) return 2;
        return 3;
    }
}
