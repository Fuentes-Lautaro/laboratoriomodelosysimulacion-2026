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
    // Formateador para mantener prolijos los decimales de los minutos
    java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");

    System.out.println("\n┌──────────────────────────────────────────────────────────────┐");
    System.out.println("│                 REPORTE DE TIEMPO DE OCIO                    │");
    System.out.println("├──────────────────────────────────────────────────────────────┤");

    if (this.totalTimeLeisure == 0) {
        System.out.printf("│ %-60s │\n", "No se ha registrado tiempo de ocio.");
    } else {
        double proporcion = (this.totalTimeLeisure / 40320.0) * 100;

        System.out.printf("│ %-48s : %6s min │\n", "Tiempo total de ocio", df.format(this.totalTimeLeisure));
        System.out.printf("│ %-48s : %9.2f%% │\n", "Proporcion respecto al tiempo de simulacion", proporcion);
        System.out.printf("│ %-48s : %6s min │\n", "Tiempo minimo de ocio", df.format(this.minTimeLeisure));
        System.out.printf("│ %-48s : %6s min │\n", "Tiempo maximo de ocio", df.format(this.maxTimeLeisure));
    }

    System.out.println("└──────────────────────────────────────────────────────────────┘\n");
}
}
