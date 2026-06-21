package com.laboratorio;

import java.util.ArrayList;
import java.util.List;

import com.laboratorio.collectors.CollectorServerStats;
import com.laboratorio.collectors.CollectorSizeQueue;
import com.laboratorio.collectors.CollectorTimeOnSystem;
import com.laboratorio.collectors.CollectorTimeWait;
import com.laboratorio.dominio.Engine;
import com.laboratorio.intervals.Intervals;
import com.laboratorio.models.OneToOneByInsertionOrder;
import com.laboratorio.scenario.AirportSim;     
import com.laboratorio.scenario.SelectionPolicy;


    public class App 
    {
        public static void main( String[] args )
        {
            int countLaunch = 0;
            int maxLaunchs = 45;
            int numServers = 3;
            int numQueues = 3;

            List<CollectorTimeOnSystem> collectorsToS = new ArrayList<CollectorTimeOnSystem>();
            for (int i = 0; i < maxLaunchs; i++){
                collectorsToS.add(new CollectorTimeOnSystem());
            }

            List<CollectorTimeWait> collectorsTW = new ArrayList<CollectorTimeWait>();
            for (int i = 0; i < maxLaunchs; i++){
                collectorsTW.add(new CollectorTimeWait());
            }

            List<CollectorSizeQueue> collectorsSQ = new ArrayList<CollectorSizeQueue>();
            for (int i = 0; i < maxLaunchs; i++){
                collectorsSQ.add(new CollectorSizeQueue());
            }

            List<List<CollectorServerStats>> collectorsST = new ArrayList<List<CollectorServerStats>>();
            for (int i = 0; i < maxLaunchs; i++){
                List<CollectorServerStats> cST = new ArrayList<CollectorServerStats>();
                for (int j = 0; j < numServers; j++)
                    cST.add(new CollectorServerStats());
                collectorsST.add(cST);
            }

            while (countLaunch < maxLaunchs){

                Engine e = new AirportSim(
                    40320d,
                    numServers,
                    numQueues,
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

            List<Double> entitiesArrival = new ArrayList<Double>();
            List<Double> entitiesEoS = new ArrayList<Double>();
            List<Double> maxTimeToS = new ArrayList<Double>();
            List<Double> minTimeToS = new ArrayList<Double>();
            List<Double> averageTimeToS = new ArrayList<Double>();
            for (CollectorTimeOnSystem c : collectorsToS){
                entitiesArrival.add(c.getTotalEntitiesArrival());
                entitiesEoS.add(c.getTotalEntitiesEoS());
                maxTimeToS.add(c.getMaxTimeToS());
                minTimeToS.add(c.getMinTimeToS());
                averageTimeToS.add(c.getTotalTimeToS() / c.getTotalEntitiesEoS());
            }

            List<Double> entitiesWait = new ArrayList<Double>();
            List<Double> maxTimeWait = new ArrayList<Double>();
            List<Double> minTimeWait = new ArrayList<Double>();
            List<Double> averageTimeWait = new ArrayList<Double>();
            for (CollectorTimeWait c : collectorsTW){
                entitiesWait.add(c.getTotalEntitiesWait());
                maxTimeWait.add(c.getMaxTimeWait());
                minTimeWait.add(c.getMinTimeWait());
                averageTimeWait.add(c.getTotalTimeWait() / c.getTotalEntitiesWait());
            }

            List<Double> maxQueueList = new ArrayList<Double>();

            for (CollectorSizeQueue c : collectorsSQ){
                maxQueueList.add(c.getMaxSizeQueue());
            }

            Intervals interval = new Intervals();
            
            String mainSeparator = "=======================================================================================";
            String subSeparator  = "---------------------------------------------------------------------------------------";

            System.out.println("\n" + mainSeparator);
            System.out.println(" [>>>] RESULTADOS DE INTERVALOS DE TIEMPO EN SISTEMA (en minutos)");
            System.out.println(mainSeparator);

            interval.calculateInterval(entitiesArrival, 1.96d);
            System.out.printf("  [*] %-60s %s%n", "Arribos:", interval.toString());

            interval.calculateInterval(entitiesEoS, 1.96d);
            System.out.printf("  [*] %-60s %s%n", "Salidas:", interval.toString());

            interval.calculateInterval(maxTimeToS, 1.96d);
            System.out.printf("  [*] %-60s %s%n", "Tiempo Máximo:", interval.toString());

            interval.calculateInterval(minTimeToS, 1.96d);
            System.out.printf("  [*] %-60s %s%n", "Tiempo Mínimo:", interval.toString());

            interval.calculateInterval(averageTimeToS, 1.96d);
            System.out.printf("  [*] %-60s %s%n", "Tiempo Promedio:", interval.toString());


            System.out.println("\n" + mainSeparator);
            System.out.println(" [>>>] RESULTADOS DE INTERVALOS DE TIEMPO DE ESPERA (en minutos)");
            System.out.println(mainSeparator);

            interval.calculateInterval(entitiesWait, 1.96d);
            System.out.printf("  [+] %-60s %s%n", "Total Entidades:", interval.toString());

            interval.calculateInterval(maxTimeWait, 1.96d);
            System.out.printf("  [+] %-60s %s%n", "Tiempo Máximo:", interval.toString());

            interval.calculateInterval(minTimeWait, 1.96d);
            System.out.printf("  [+] %-60s %s%n", "Tiempo Mínimo:", interval.toString());

            interval.calculateInterval(averageTimeWait, 1.96d);
            System.out.printf("  [+] %-60s %s%n", "Tiempo Promedio:", interval.toString());


            System.out.println("\n" + mainSeparator);
            System.out.println(" [>>>] RESULTADOS DE INTERVALOS DE TAMANO DE LA COLA (en cantidad de entidades)");
            System.out.println(mainSeparator);

            interval.calculateInterval(maxQueueList, 1.96d);
            System.out.printf("  [*] %-60s %s%n", "Tamano Maximo:", interval.toString());


            System.out.println("\n" + mainSeparator);
            System.out.println(" [>>>] RESULTADOS DE INTERVALOS DE ESTADISTICAS DE LOS SERVIDORES (en minutos)");
            System.out.println(mainSeparator);
            
            for (int i = 0; i < numServers; i++){
                int aux = i + 1;
                List<Double> maxTimeLeisure = new ArrayList<Double>();
                List<Double> minTimeLeisure = new ArrayList<Double>();
                List<Double> finalDurability = new ArrayList<Double>();
                List<Double> averageTimeLeisure = new ArrayList<Double>();
                
                System.out.println(subSeparator);
                System.out.println("  -> SERVER " + aux);
                System.out.println(subSeparator);
                
                for (List<CollectorServerStats> c : collectorsST){
                    maxTimeLeisure.add(c.get(i).getMaxTimeLeisure());
                    minTimeLeisure.add(c.get(i).getMinTimeLeisure());
                    finalDurability.add(c.get(i).getFinalDurability());
                    averageTimeLeisure.add(c.get(i).getTotalTimeLeisure() * 100 / 40320);
                }

                interval.calculateInterval(maxTimeLeisure, 1.96d);
                System.out.printf("    > %-60s %s%n", "Tiempo Máximo de Ocio:", interval.toString());

                interval.calculateInterval(minTimeLeisure, 1.96d);
                System.out.printf("    > %-60s %s%n", "Tiempo Mínimo de Ocio:", interval.toString());

                interval.calculateInterval(averageTimeLeisure, 1.96d);
                System.out.printf("    > %-60s %s%n", "Proporcion de ocio sobre tiempo total (%):", interval.toString());

                interval.calculateInterval(finalDurability, 1.96d);
                System.out.printf("    > %-60s %s%n", "Durabilidad final:", interval.toString());
            }

            System.out.println("\n" + mainSeparator);
            System.out.printf(" [i] FIN DE LA SIMULACION - EJECUCIONES TOTALES REALIZADAS: %d%n", maxLaunchs);
            
            System.out.println(mainSeparator + "\n");
        }
    }