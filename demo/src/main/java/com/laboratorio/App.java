package com.laboratorio;

import com.laboratorio.dominio.Engine;
import com.laboratorio.scenario.AirportSim;

public class App 
{
    public static void main( String[] args )
    {
        Engine e = new AirportSim(100d);
        e.run();
        
    }
}
