package com.laboratorio.scenario;
import com.laboratorio.dominio.Collector;

public class CollectorSizeQueue implements Collector{
    private double maxSizeQueue;

    public CollectorSizeQueue() {
        this.maxSizeQueue = Double.MIN_VALUE;
    }

    @Override
    public void collect(double sizeQueue) {
        if (sizeQueue > this.maxSizeQueue) {
            this.maxSizeQueue = sizeQueue;
        }
    }

    @Override
    public void printReport() {
        System.out.println(" ---   REPORTE DE TAMAÑO DE COLA --- ");
        System.out.println("Tamaño máximo: " + this.maxSizeQueue);
        System.out.println("");
    }
}
