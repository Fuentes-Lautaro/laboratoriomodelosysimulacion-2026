package com.laboratorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


public class FutureEventList {
    private List<Event> fel;
    private final Comparator<Event> comparator; 

    public FutureEventList(){
        this.fel = new ArrayList<>();
        this.comparator = new Comparator<Event>(){
            @Override
            public int compare (Event e1, Event e2){
                 if (e1.getClock() < e2.getClock()){
                    return -1; //e1 < e2       
                }else if (e1.getClock() > e2.getClock()){
                    return 1; //e1 > e2
                }else{
                    if (e1.getOrder() < e2.getOrder() ) {
                    return -1; //si e1 < e2
                }else if(e1.getOrder() > e2.getOrder()){
                    return 1; //si e1 > e2
                }else{
                    return 0; //SI SON IGUALES TOMA PRIORIDAD LA SALIDA
            }
        }
    }
        };
    }

    public void insert(Event e){
        this.fel.add(e);
        this.fel.sort(this.comparator);
    }

    public Event imminent(){
        return this.fel.remove(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Event e : this.fel) {
            sb.append(String.format("%s: %f\n", e.getClass().getSimpleName(), e.getClock()));
        }
        return sb.toString();
    }
}
