package com.laboratorio;

public class TiempoEntreArribos {

    public static int tiempoEntreArribos(){
        double tea = Realeatorizador.nroRandom();
        if (tea < 0.35) return 10;
        if (tea < 0.8) return 15;
        return 17;
    }
}
