package com.laboratorio.scenario;

public class Collector {
    private int totalEntitiesArrival;
    private int totalEntitiesEoS;
    private double maxTime;
    private double minTime;
    private double totalTime;

    public Collector() {
        this.totalEntitiesArrival = 0;
        this.totalEntitiesEoS = 0;
        this.maxTime = 0;
        this.minTime = 0;
        this.totalTime = 0;
    }

    public void collectArrival() {
        this.totalEntitiesArrival++;
    }

    public void collectEoS(double timeEoS, double time) {
        this.totalEntitiesEoS++;
        double timeInSystem = time - timeEoS;
        this.totalTime += timeInSystem;
        if (timeInSystem > this.maxTime) {
            this.maxTime = timeInSystem;
        }
        if (this.minTime == 0 || timeInSystem < this.minTime) {
            this.minTime = timeInSystem;
        }
    }

    public void printReport() {
        System.out.println("Total de entidades que arribaron al sistema: " + this.totalEntitiesArrival);
        System.out.println("Total de entidades que salieron del sistema: " + this.totalEntitiesEoS);
        System.out.println("Tiempo máximo en el sistema: " + this.maxTime);
        System.out.println("Tiempo mínimo en el sistema: " + this.minTime);
        if (this.totalEntitiesEoS > 0) {
            System.out.println("Tiempo promedio en el sistema: " + (this.totalTime / this.totalEntitiesEoS));
        } else {
            System.out.println("Ninguna entidad ha salido del sistema, no se puede calcular el tiempo promedio.");
        }
    }
}