package com.laboratorio.scenario;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;

public class Arrival implements Event {
    private final double clock;
    private final int order;
    private final Entity entity;
    private Distribution arrivalDistribution;
    private Distribution EoSDistribution;

    public Arrival(Double clock, Entity entity, Distribution arrivalDistribution, Distribution EoSDistribution) {
        this.clock = clock;
        this.order = 10;
        this.entity = entity;
        this.arrivalDistribution = arrivalDistribution;
        this.EoSDistribution = EoSDistribution;
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
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public Distribution getDistribution() {
        return this.arrivalDistribution;
    }

    @Override
    public void planificate(FutureEventList fel, Server server){

        if (server.isBusy()){ //CONSULTO SI SERVER ESTA OCUPADO
            server.getQueue().enqueue(this.entity);

        }else{
            server.setEntity(this.entity);
            
            fel.insert(new EndOfService(this.clock + this.EoSDistribution.sample(), this.entity, this.EoSDistribution)); //INSERTO EL EVENTO SALIDA DEL ELEMENTO ACTUAL
        }

        fel.insert(new Arrival(this.clock + this.arrivalDistribution.sample(), new Entity(), this.arrivalDistribution, this.EoSDistribution)); //INSERTO EL NUEVO EVENTO DE ARRIBO

        //COLECCIONO ESTADISTICAS
        //TERMINA EL PLANIFICATE
    }
}
