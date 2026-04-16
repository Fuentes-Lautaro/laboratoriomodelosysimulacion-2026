/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.laboratorio.scenario;

import com.laboratorio.dominio.Engine;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;

/**
 *
 * @author eldem
 */
public class AirportSim implements Engine {

    private final double simLenght;
    private FutureEventList fel;
    private Server server;

    public AirportSim(double simLenght){
        this.simLenght = simLenght;
        this.fel = new FutureEventList();
        this.fel.insert(new Arrival(0d, new Entity(), new Table1(), new Table2())) ;
        this.server = new Airstrip(1, new MyQueue());
    }
    
    @Override
    public void run() {

        System.out.println(this.fel);
        Event e = this.fel.imminent();
        double clock = e.getClock();

        while (clock < this.simLenght) { 

            e.planificate(this.fel, this.server);

            System.out.println(this.fel);

            e = this.fel.imminent();
            clock = e.getClock();
        }
    }

}
