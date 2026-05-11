/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.laboratorio.scenario;

import java.util.List;

import com.laboratorio.collectors.CollectorSizeQueue;
import com.laboratorio.collectors.CollectorTimeLeisure;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.distribution.EmpiricaDiscreta;
import com.laboratorio.distribution.Table2;
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
    private List<Server> servers    ;
    private CollectorTimeOnSystem collectorToS;
    private CollectorTimeWait collectorWait;
    private CollectorSizeQueue collectorSQ;
    private CollectorTimeLeisure collectorTL;

    private final ServerSelectionPolicy policy;

    public AirportSim(double simLenght, List<Server> servers, ServerSelectionPolicy policy) {
        this.simLenght = simLenght;
        this.fel = new FutureEventList();
        this.collectorToS = new CollectorTimeOnSystem();
        this.collectorWait = new CollectorTimeWait();
        this.collectorSQ = new CollectorSizeQueue();
        this.collectorTL = new CollectorTimeLeisure();
        this.servers = servers;
        this.fel.insert(new Arrival(0d, new Entity(), new EmpiricaDiscreta(), new Table2(), this.collectorToS, this.collectorWait, this.collectorSQ, this.collectorTL)) ;

        this.policy = policy;
    }
    
    @Override
    public void run() {

        System.out.println(this.fel);

        Event e = this.fel.imminent();

        double clock = e.getClock();

        while (clock < this.simLenght) { 

            Server selectedServer = this.policy.selectServer(this.servers);

            e.planificate(this.fel, selectedServer);

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
