package com.laboratorio;

import java.util.List;

import com.laboratorio.dominio.Engine;
import com.laboratorio.scenario.AirportSim;
import com.laboratorio.scenario.Airstrip;
import com.laboratorio.scenario.MyQueue;


public class App 
{
    public static void main( String[] args )
    {

        
        Engine e = new AirportSim(
            40320d,
            List.of(new Airstrip(1, new MyQueue())),
            ss -> ss.get(0)
        );
        
        e.run();
        
    }
}
