package com.laboratorio.dominio;

public class Entity {
    
    private static int contador = 0;
    private final int id;
    private double timeArrival;

    public Entity(){
        contador++;
        this.id = contador;
    }

    public void setTimeArrival(double timeArrival){
        this.timeArrival = timeArrival;
    }

    public double getTimeArrival(){
        return this.timeArrival;
    }

    public int getId() {
        return id;
    }
}
