    package com.laboratorio;

    import java.util.List;

    import com.laboratorio.dominio.Engine;
    import com.laboratorio.scenario.AirportSim;
    import com.laboratorio.scenario.Airstrip;
    import com.laboratorio.scenario.MyQueue;
    import com.laboratorio.scenario.SelectionPolicy;
    import com.laboratorio.collectors.CollectorSizeQueue;
    import com.laboratorio.collectors.CollectorTimeOnSystem;
    import com.laboratorio.collectors.CollectorTimeLeisure;


    public class App 
    {
        public static void main( String[] args )
        {
            int countLaunch = 0;
            int maxLaunchs = 2;
            List listServers = new java.util.ArrayList<Airstrip>();
            for (int i = 1; i <= 3; i++){
                listServers.add(new Airstrip(i, new MyQueue()));
            }

            List<CollectorTimeOnSystem> collectorsToS = new java.util.ArrayList<CollectorTimeOnSystem>();
            for (int i = 0; i <= maxLaunchs; i++){
                collectorsToS.add(new CollectorTimeOnSystem());
            }

            List<CollectorSizeQueue> collectorsSQ = new java.util.ArrayList<CollectorSizeQueue>();
            for (int i = 0; i <= maxLaunchs; i++){
                collectorsSQ.add(new CollectorSizeQueue());
            }

            while (countLaunch <= maxLaunchs){
            
                Engine e = new AirportSim(
                        100d,
                        listServers,
                        new SelectionPolicy(),
                        collectorsSQ.get(countLaunch),
                        collectorsToS.get(countLaunch)
                    );

                e.run();

                countLaunch++;
            }

            for (CollectorTimeOnSystem c : collectorsToS){
                c.printReport();
            }
        }
    }
