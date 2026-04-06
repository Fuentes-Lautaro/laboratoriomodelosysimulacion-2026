package com.laboratorio;

public class Arrival implements Event {
    private final double clock;
    private final int order;

    public Arrival(Double clock) {
        this.clock = clock;
        this.order = 10;
    }

    @Override
    public double getClock(){
        return clock;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public void planificate(FutureEventList fel, Server server){
        if (server.libre == true){ //CONSULTO SI SERVER ESTA OCUPADO
            // server = 
            int tS = DuracionInspeccion.tiempoSalida(); //GENERO UN TIEMPO DE SERVICIO
            fel.insert(new EndOfService(this.clock + tS)); //INSERTO EL EVENTO SALIDA DEL ELEMENTO ACTUAL
        }else{
            //agrego el evento a la cola
        }
        int tea = TiempoEntreArribos.tiempoEntreArribos(); //GENERO UN NUEVO TIEMPO ENTRE ARRIBOS
        fel.insert(new Arrival(this.clock + tea)); //INSERTO EL NUEVO EVENTO DE ARRIBO

        //COLECCIONO ESTADISTICAS
    }
}
