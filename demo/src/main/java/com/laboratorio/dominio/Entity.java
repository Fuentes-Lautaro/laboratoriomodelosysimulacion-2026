package com.laboratorio.dominio;

public class Entity {
    
    private static int contador  = 0;
    private final int id;
    private double tiempoArribo;

    public Entity() {
        contador++;
        this.id = contador;
    }

    public void setTiempoArribo(double tiempoArribo){
        this.tiempoArribo = tiempoArribo;
    }
}
