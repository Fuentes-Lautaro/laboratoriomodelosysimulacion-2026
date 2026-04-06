package com.laboratorio;

public class Avion {
    private static int contador  = 0;
    private int id;
    private double tiempoArribo;

    public void Avion(double tiempoArribo){
        contador++;
        this.id = contador;
        this.tiempoArribo = tiempoArribo;
    }
}
