package com.laboratorio;

import java.util.List;

import com.laboratorio.dominio.Engine;
import com.laboratorio.scenario.AirportSim;
import com.laboratorio.scenario.Airstrip;
import com.laboratorio.scenario.MyQueue;
import com.laboratorio.scenario.SelectionPolicy;


public class App 
{
    public static void main( String[] args )
    {
        int countLaunch = 0;
        List listServers = new java.util.ArrayList<Airstrip>();
        for (int i = 1; i <= 3; i++){
            listServers.add(new Airstrip(i, new MyQueue()));
        }

        while (countLaunch < 3){
        
            Engine e = new AirportSim(
                    100d,
                    listServers,
                    new SelectionPolicy()
                );

            e.run();

            countLaunch++;
        }
    }
}
