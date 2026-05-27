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
        
        List listServers = new java.util.ArrayList<Airstrip>();
        for (int i = 1; i <= 5; i++){
            listServers.add(new Airstrip(i, new MyQueue()));
        }

        Engine e = new AirportSim(
                40320d,
                listServers,
                new SelectionPolicy()
                );

        e.run();

    }
}