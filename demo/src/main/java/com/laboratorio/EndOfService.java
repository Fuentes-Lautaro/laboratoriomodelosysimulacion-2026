package com.laboratorio;

public class EndOfService implements Event {
    private double clock;
    private int order;

    public EndOfService(Double clock) {
        this.clock = clock;
        this.order = 0;
    }

    public double clock(){
        return clock;
    }

    public int order() {
        return order;
    }
    
    public void planificate(FutureEventList fel, Server server){
        if (server == NULL){ //PREGUNTO SI EL SERVER ESTA OCUPADO
            //COLA = COLA - 1, PREGUNTAR
            int tiempoServicio = tiempoSalida();
            //PLANIFICO EL PROXIMO FIN DE SERVICIO
            fel.insert(new EndOfService(this.clock + tiempoServicio));
        }else{
            //SERVER == DESOCUPADO
        }
        //COLECCIONAR ESTADISTICAS
        //TERMINA EL PLANIFICATE
    }
}