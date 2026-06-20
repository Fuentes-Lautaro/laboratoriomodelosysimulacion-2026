package com.laboratorio;

import java.util.List;

import com.laboratorio.collectors.CollectorServerStats;
import com.laboratorio.collectors.CollectorSizeQueue;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.dominio.Engine;
import com.laboratorio.models.OneToOneByInsertionOrder;
import com.laboratorio.scenario.AirportSim;
import com.laboratorio.scenario.SelectionPolicy;


    public class App 
    {
        public static void main( String[] args )
        {
            int countLaunch = 0;
            int maxLaunchs = 3;
            int numServers = 3;

            List<CollectorTimeOnSystem> collectorsToS = new java.util.ArrayList<CollectorTimeOnSystem>();
            for (int i = 0; i < maxLaunchs; i++){
                collectorsToS.add(new CollectorTimeOnSystem());
            }

            List<CollectorTimeWait> collectorsTW = new java.util.ArrayList<CollectorTimeWait>();
            for (int i = 0; i < maxLaunchs; i++){
                collectorsTW.add(new CollectorTimeWait());
            }

            List<CollectorSizeQueue> collectorsSQ = new java.util.ArrayList<CollectorSizeQueue>();
            for (int i = 0; i < maxLaunchs; i++){
                collectorsSQ.add(new CollectorSizeQueue());
            }

            List<List<CollectorServerStats>> collectorsST = new java.util.ArrayList<List<CollectorServerStats>>();
            for (int i = 0; i < maxLaunchs; i++){
                List<CollectorServerStats> cST = new java.util.ArrayList<CollectorServerStats>();
                for (int j = 0; j < numServers; j++)
                    cST.add(new CollectorServerStats());
                collectorsST.add(cST);
            }

            while (countLaunch < maxLaunchs){

                Engine e = new AirportSim(
                    40320d,
                    numServers,
                    3,
                    new OneToOneByInsertionOrder(),
                    new SelectionPolicy(),
                    collectorsToS.get(countLaunch),
                    collectorsTW.get(countLaunch),
                    collectorsSQ.get(countLaunch),
                    collectorsST.get(countLaunch)
                    );

                e.run();
                countLaunch++;
            }

            for (CollectorTimeOnSystem c : collectorsToS){
                
            }

            for (CollectorTimeWait c : collectorsTW){
                c.printReport();
            }

            for (CollectorSizeQueue c : collectorsSQ){
                c.printReport();
            }

            for (List<CollectorServerStats> c : collectorsST){
                for (CollectorServerStats v : c){
                    v.printReport();
                }
            }
        }
    }