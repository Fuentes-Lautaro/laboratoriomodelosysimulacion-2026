/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.laboratorio.scenario;

import java.util.ArrayList;
import java.util.List;

import com.laboratorio.behaviors.RushHour;
import com.laboratorio.behaviors.SingleBehavior;
import com.laboratorio.collectors.CollectorServerStats;
import com.laboratorio.collectors.CollectorSizeQueue;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.distribution.Exponencial;
import com.laboratorio.distribution.Normal;
import com.laboratorio.distribution.Uniforme;
import com.laboratorio.dominio.Engine;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Event;
import com.laboratorio.dominio.FutureEventList;
import com.laboratorio.dominio.ModelSpecificator;
import com.laboratorio.dominio.Queue;
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
    private List<Queue> queues;
    private ServerSelectionPolicy serverSelectionPolicy;
    private CollectorTimeOnSystem collectorToS;
    private CollectorTimeWait collectorWait;
    private CollectorSizeQueue collectorSQ;
    private CollectorServerStats collectorST;

    public AirportSim(double simLenght, int numServers, int numQueues, ModelSpecificator model,
                        ServerSelectionPolicy serverSelectionPolicy, 
                        CollectorTimeOnSystem collectorToS, CollectorTimeWait collectorTW,
                        CollectorSizeQueue collectorSQ, CollectorServerStats collectorST){
        
        this.servers = new ArrayList<>();
        for (int i=1; i < numServers+1; i++){
            this.servers.add(new Airstrip(i));
        }
                            
        this.queues = new ArrayList<>();
        for (int i=1; i <= numQueues+1; i++){
            this.queues.add(new MyQueue());
        }
        
        model.specificate(servers, queues);

        this.simLenght = simLenght;

        this.fel = new FutureEventList();
        
        this.collectorToS = collectorToS;
        this.collectorWait = collectorTW;
        this.collectorSQ = collectorSQ;
        this.collectorST = collectorST;
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
                        this.serverSelectionPolicy,
                        new RushHour(),
                        new SingleBehavior(0)));

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
    }
}