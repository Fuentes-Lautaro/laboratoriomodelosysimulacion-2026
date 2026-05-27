package com.laboratorio.collectors;
import com.laboratorio.dominio.Collector;

public class CollectorTimeWait implements Collector{
    private double totalTimeWait;
    private int totalEntities;
    private double maxTimeWait;
    private double minTimeWait;

    public CollectorTimeWait() {
        this.totalTimeWait = 0;
        this.totalEntities = 0;
        this.maxTimeWait = Double.MIN_VALUE;
        this.minTimeWait = Double.MAX_VALUE;
    }

    @Override
    public void collect(double timeWait) {
        this.totalTimeWait += timeWait;
        this.totalEntities++;
        if (timeWait > this.maxTimeWait) {
            this.maxTimeWait = timeWait;
        }
        if (timeWait < this.minTimeWait) {
            this.minTimeWait = timeWait;
        }
    }

    @Override
    public void printReport() {
        System.out.println(" ---   REPORTE DE TIEMPO DE ESPERA --- ");
        System.out.println("Total de entidades que esperaron: " + this.totalEntities + " entidades.");
        if (this.totalEntities != 0) {
            System.out.println("Tiempo máximo de espera: " + this.maxTimeWait + " minutos.");
            System.out.println("Tiempo mínimo de espera: " + this.minTimeWait + " minutos");
            System.out.println("Tiempo medio de espera: " + (this.totalTimeWait / this.totalEntities) + " minutos.");
        }
        
        System.out.println("");
    }
}
