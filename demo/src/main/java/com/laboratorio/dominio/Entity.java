package com.laboratorio.dominio;

public class Entity {
    
    private static int contador = 0;
    private final int id;
    private double timeArrival;
    private Server server;

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

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
