package com.laboratorio.collectors;
import com.laboratorio.dominio.Collector;

public class CollectorTimeOnSystem implements Collector{
    private int totalEntitiesArrival;
    private int totalEntitiesEoS;
    private double maxTime;
    private double minTime;
    private double totalTime;

    public CollectorTimeOnSystem() {
        this.totalEntitiesArrival = 0;
        this.totalEntitiesEoS = 0;
        this.totalTime = 0;
        this.maxTime = Double.MIN_VALUE;
        this.minTime = Double.MAX_VALUE;
    }

    public void collectArrival() {
        this.totalEntitiesArrival++;
    }

    @Override
    public void collect(double timeEoS) {
        this.totalEntitiesEoS++;
        double timeInSystem = timeEoS;
        this.totalTime += timeInSystem;
        if (timeInSystem > this.maxTime) {
            this.maxTime = timeInSystem;
        }
        if (timeInSystem < this.minTime) {
            this.minTime = timeInSystem;
        }
    }

    public int getTotalEntitiesArrival() {
        return totalEntitiesArrival;
    }
    public int getTotalEntitiesEoS() {
        return totalEntitiesEoS;
    }


    public double getMaxTimeToS() {
        return maxTime;
    }

    public double getMinTimeToS() {
        return minTime;
    }

    @Override
    public void printReport() {
        System.out.println(" ---   REPORTE DEL SISTEMA --- ");
        System.out.println("Total de entidades que arribaron al sistema: " + this.totalEntitiesArrival);
        System.out.println("Total de entidades que salieron del sistema: " + this.totalEntitiesEoS);
        System.out.println("");
        System.out.println(" ---   REPORTE DE TIEMPO EN EL SISTEMA   --- ");
        System.out.println("Tiempo máximo en el sistema: " + this.maxTime);
        System.out.println("Tiempo mínimo en el sistema: " + this.minTime);
        if (this.totalEntitiesEoS > 0) {
            System.out.println("Tiempo promedio en el sistema: " + (this.totalTime / this.totalEntitiesEoS));
        } else {
            System.err.println("Ninguna entidad ha salido del sistema, no se puede calcular el tiempo promedio.");
        }
        System.out.println("");
    }
}