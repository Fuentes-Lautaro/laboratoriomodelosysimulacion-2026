package com.laboratorio.collectors;
import com.laboratorio.dominio.Collector;

public class CollectorTimeLeisure implements Collector{
    private double totalTimeLeisure;
    private double minTimeLeisure;
    private double maxTimeLeisure;

    public CollectorTimeLeisure() {
        this.totalTimeLeisure = 0.0;
        this.minTimeLeisure = Double.MAX_VALUE;
        this.maxTimeLeisure = Double.MIN_VALUE;
    }

    @Override
    public void collect(double timeLeisure) {
        this.totalTimeLeisure += timeLeisure;
        if (timeLeisure > 0 && timeLeisure < this.minTimeLeisure) {
            this.minTimeLeisure = timeLeisure;
        }
        if (timeLeisure > this.maxTimeLeisure) {
            this.maxTimeLeisure = timeLeisure;
        }
    }

    public double getTotalTimeLeisure() {
        return totalTimeLeisure;
    }

    public double getAverageTimeLeisure() {
        return (this.totalTimeLeisure / 40320.0) * 100;
    }

    public double getMinTimeLeisure() {
        return minTimeLeisure;
    }

    public double getMaxTimeLeisure() {
        return maxTimeLeisure;
    }

    @Override
    public void printReport() {
        System.out.println(" ---   REPORTE DE TIEMPO DE OCIO --- ");
        if (totalTimeLeisure == 0) {
            System.out.println("No se ha registrado tiempo de ocio.");
            return;
        }
        System.out.println("Tiempo total de ocio: " + this.totalTimeLeisure);
        System.out.printf("Tiempo total en proporcion del tiempo de simulacion: %.2f%%\n", (this.totalTimeLeisure / 40320.0) * 100);
        System.out.println("Tiempo mínimo de ocio: " + this.minTimeLeisure);
        System.out.println("Tiempo máximo de ocio: " + this.maxTimeLeisure);
        System.out.println("");
    }
}
