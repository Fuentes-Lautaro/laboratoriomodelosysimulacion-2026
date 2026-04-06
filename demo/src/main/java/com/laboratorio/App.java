package com.laboratorio;


public class App 
{
    public static void main( String[] args )
    {
        FutureEventList fel = new FutureEventList();
        fel.insert(new Arrival(0.0));
        double clock = 0.0;
        while (clock < 60){
            Event e = fel.imminent();
            e.planificate(fel, null);
            clock = e.getClock();
        }
    }
}
