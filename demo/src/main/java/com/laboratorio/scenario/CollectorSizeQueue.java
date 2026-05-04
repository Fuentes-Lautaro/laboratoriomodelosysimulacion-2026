package com.laboratorio.scenario;
import com.laboratorio.dominio.Collector;

public class CollectorSizeQueue implements Collector{
    private double maxSizeQueue;
    private double minSizeQueue;

    public CollectorSizeQueue() {
        this.maxSizeQueue = 0;
        this.minSizeQueue = 0;
    }

    @Override
    public void collect(double sizeQueue) {
        if (sizeQueue > this.maxSizeQueue) {
            this.maxSizeQueue = sizeQueue;
        }
        if (this.minSizeQueue == 0) {
            this.minSizeQueue = sizeQueue;
        }else if (sizeQueue < this.minSizeQueue) {
            this.minSizeQueue = sizeQueue;
        }
    }

    @Override
    public void printReport() {
        System.out.println(" ---   REPORTE DE TAMAÑO DE COLA --- ");
        System.out.println("Tamaño máximo: " + this.maxSizeQueue);
        if (minSizeQueue == 0){
            System.err.println("No hubo cola en ningun instante.");
        }else{
            System.out.println("Tamaño mínimo: " + this.minSizeQueue);
        }
        System.out.println("");
    }
}
