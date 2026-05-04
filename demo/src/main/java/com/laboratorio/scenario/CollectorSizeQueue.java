package com.laboratorio.scenario;

public class CollectorSizeQueue {
    private int maxSizeQueue;
    private int minSizeQueue;

    public CollectorSizeQueue() {
        this.maxSizeQueue = 0;
        this.minSizeQueue = 0;
    }

    public void collect(int sizeQueue) {
        if (sizeQueue > this.maxSizeQueue) {
            this.maxSizeQueue = sizeQueue;
        }
        if (sizeQueue < this.minSizeQueue || this.minSizeQueue == 0) {
            this.minSizeQueue = sizeQueue;
        }
    }

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
