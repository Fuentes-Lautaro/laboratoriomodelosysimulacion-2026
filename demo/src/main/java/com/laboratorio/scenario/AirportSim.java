/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.laboratorio.scenario;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import com.laboratorio.behaviors.SingleBehavior;
import com.laboratorio.behaviors.RushHour;
import com.laboratorio.collectors.CollectorSizeQueue;
import com.laboratorio.collectors.CollectorTimeLeisure;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.distribution.Exponencial;
import com.laboratorio.distribution.Exponential2;
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
    private CollectorTimeOnSystem collectorToS;
    private CollectorTimeWait collectorWait;
    private CollectorSizeQueue collectorSQ;
    private CollectorTimeLeisure collectorTL;

    public AirportSim(double simLenght) {
        this.simLenght = simLenght;

        this.fel = new FutureEventList();
        
        this.collectorToS = new CollectorTimeOnSystem();
        this.collectorWait = new CollectorTimeWait();
        this.collectorSQ = new CollectorSizeQueue();
        this.collectorTL = new CollectorTimeLeisure();

        this.fel.insert(
                new Arrival(0d,
                        new Entity(),
                        new ArrayList<>() {
                            {
                                add(new Exponencial(9));
                                add(new Exponencial(15));
                            }
                        },
                        new ArrayList<>() {
                            {
                                add(new Exponencial(15));
                            }
                        },
                        this.collectorToS, 
                        this.collectorWait, 
                        this.collectorSQ, 
                        this.collectorTL, 
                        new RushHour(),
                        new SingleBehavior(0)));

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
        this.collectorToS.printReport();
        this.collectorWait.printReport();
        this.collectorSQ.printReport();
        this.collectorTL.printReport();
    }

}
