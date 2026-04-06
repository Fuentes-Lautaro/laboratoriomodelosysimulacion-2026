package com.laboratorio;

public class Arrival implements Event {
    private double clock;
    private int order;

    public Arrival(Double clock) {
        this.clock = clock;
        this.order = 10;
    }

    public double getClock(){
        return clock;
    }

    public int getOrder() {
        return order;
    }

    public void planificate(FutureEventList fel, Server server){
        if (server == null){ //CONSULTO SI SERVER ESTA OCUPADO
            // server = 
            int tS = tiempoSalida(); //GENERO UN TIEMPO DE SERVICIO
            fel.insert(new EndOfService(this.clock + tS)); //INSERTO EL EVENTO SALIDA DEL ELEMENTO ACTUAL
        }else{
            //agrego el evento a la cola
        }
        int tea = tiempoEntreArribos(); //GENERO UN NUEVO TIEMPO ENTRE ARRIBOS
        fel.insert(new Arrival(this.clock + tea))); //INSERTO EL NUEVO EVENTO DE ARRIBO

        //COLECCIONO ESTADISTICAS
    }
}
