/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.laboratorio.scenario;

import java.util.ArrayList;
import java.util.List;

import com.laboratorio.behaviors.RushHour;
import com.laboratorio.behaviors.SingleBehavior;
import com.laboratorio.collectors.CollectorSizeQueue;
import com.laboratorio.collectors.CollectorTimeLeisure;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.distribution.Exponencial;
import com.laboratorio.distribution.Normal;
import com.laboratorio.distribution.Uniforme;
import com.laboratorio.dominio.Engine;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.Server;
import com.laboratorio.dominio.ServerSelectionPolicy;

/**
 *
 * @author eldem
 */
public class AirportSim implements Engine {

    private final double simLenght;
    private FutureEventList fel;
    private List<Server> servers;
    private ServerSelectionPolicy serverSelectionPolicy;
    private CollectorTimeOnSystem collectorToS;
    private CollectorTimeWait collectorWait;
    private CollectorSizeQueue collectorSQ;
    private CollectorTimeLeisure collectorTL;

    public AirportSim(double simLenght, List<Server> servers, ServerSelectionPolicy serverSelectionPolicy) {
        this.simLenght = simLenght;

        this.fel = new FutureEventList();
        
        this.collectorToS = new CollectorTimeOnSystem();
        this.collectorWait = new CollectorTimeWait();
        this.collectorSQ = new CollectorSizeQueue();
        this.collectorTL = new CollectorTimeLeisure();
        this.serverSelectionPolicy = serverSelectionPolicy;

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
                                add(new Uniforme(10, 25));
                            }
                        },
                        new Normal(5, 1),
                        this.collectorToS, 
                        this.collectorWait, 
                        this.collectorSQ, 
                        this.collectorTL, 
                        this.serverSelectionPolicy,
                        new RushHour(),
                        new SingleBehavior(0)));

        this.servers = servers;
    }

    @Override
    public void run() {

        System.out.println(this.fel);

        Event e = this.fel.imminent();

        double clock = e.getClock();

        while (clock < this.simLenght) {

            e.planificate(this.fel, this.servers);

            System.out.println(this.fel);

            e = this.fel.imminent();
            clock = e.getClock();
        }
        
        this.collectorToS.printReport();
        this.collectorWait.printReport();
        this.collectorSQ.printReport();
        this.collectorTL.printReport();

        System.out.println("\n┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│               DURABILIDAD FINAL DE LAS PISTAS                │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");

        for (Server s : this.servers) {
            String etiquetaPista = "Durabilidad de la pista " + s.getId();

        System.out.printf("│ %-48s : %10.2f │\n", etiquetaPista, s.getDurability());
        }

        System.out.println("└──────────────────────────────────────────────────────────────┘\n");
    }
}