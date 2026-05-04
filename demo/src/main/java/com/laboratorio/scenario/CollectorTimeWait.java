package com.laboratorio.scenario;
import com.laboratorio.dominio.Collector;

public class CollectorTimeWait implements Collector{
    private double totalTimeWait;
    private int totalEntities;
    private double maxTimeWait;
    private double minTimeWait;

    public CollectorTimeWait() {
        this.totalTimeWait = 0;
        this.totalEntities = 0;
        this.maxTimeWait = 0;
        this.minTimeWait = 0;
    }

    @Override
    public void collect(double timeWait) {
        this.totalTimeWait += timeWait;
        this.totalEntities++;
        if (timeWait > this.maxTimeWait) {
            this.maxTimeWait = timeWait;
        }
        if (timeWait < this.minTimeWait || this.minTimeWait == 0) {
            this.minTimeWait = timeWait;
        }
    }

    @Override
    public void printReport() {
        System.out.println(" ---   REPORTE DE TIEMPO DE ESPERA --- ");
        System.out.println("Total de entidades que esperaron: " + this.totalEntities);
        System.out.println("Tiempo máximo de espera: " + this.maxTimeWait);
        System.out.println("Tiempo mínimo de espera: " + this.minTimeWait);
        if (this.totalEntities > 0) {
            System.out.println("Tiempo promedio de espera: " + (this.totalTimeWait / this.totalEntities));
        } else {
            System.err.println("Ninguna entidad ha esperado, no se puede calcular el tiempo promedio de espera.");
        }
        System.out.println("");
    }
}
