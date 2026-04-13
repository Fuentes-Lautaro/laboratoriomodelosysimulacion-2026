package com.laboratorio.scenario;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;

public class EndOfService implements Event {
    private final double clock;
    private final int order;
    private final Entity entity;

    public EndOfService(Double clock, Entity e) {
        this.clock = clock;
        this.order = 0;
        this.entity = e;
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
        if (server.getQueue().size() > 0){ //PREGUNTO SI hay alguien waiting.
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

    @Override
    public Entity getEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Distribution getDistribution() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}