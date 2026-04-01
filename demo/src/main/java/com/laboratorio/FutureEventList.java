package com;

import java.util.ArrayList;
import java.util.List;

public class FutureEventList {
    private List<Event> fel;
    private int comparator; 
    public FutureEventList(){
        this.fel = new ArrayList<>();
        this.comparator = new Comparator<Event>();
    }

    @Override
    public int compare (Event e1, Event e2){
        if (e1.clock() < e2.clock()){
            return -1; //e1 < e2       
        }else if (e1.clock() > e2.clock()){
            return 1; //e1 > e2
        }else{
            if (e1.order() < e2.order() ) {
                return -1; //si e1 < e2
            }else if(e1.order() > e2.order()){
                return 1; //si e1 > e2
            }else{
                return 0; //SI SON IGUALES TOMA PRIORIDAD LA SALIDA
            }
        }

        
    }
       
}
