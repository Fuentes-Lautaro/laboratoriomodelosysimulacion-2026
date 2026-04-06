package com.laboratorio;

public class EndOfService implements Event {
    private final double clock;
    private final int order;

    public EndOfService(Double clock) {
        this.clock = clock;
        this.order = 0;
    }

    @Override
    public double getClock(){
        return this.clock;
    }

    @Override
    public int getOrder(){
        return this.order;
    }
   
    @Override
    public void planificate(FutureEventList fel, Server server){
        if (server.libre == true){ //PREGUNTO SI EL SERVER ESTA OCUPADO
            //COLA = COLA - 1, PREGUNTAR
            int tiempoServicio = DuracionServicio.tiempoServicio();
            //PLANIFICO EL PROXIMO FIN DE SERVICIO
            fel.insert(new EndOfService(this.clock + tiempoServicio));
        }else{
            //SERVER == DESOCUPADO
        }
        //COLECCIONAR ESTADISTICAS
        //TERMINA EL PLANIFICATE
    }
}