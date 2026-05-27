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
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");

        System.out.println("\n┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                REPORTE DE TIEMPO DE ESPERA                   │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-48s : %10d │\n", "Total de entidades que esperaron", this.totalEntities);
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.println("│               ESTADISTICAS DE TIEMPO DE ESPERA               │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");

        if (this.totalEntities != 0) {
            double tiempoMedioWait = (double) this.totalTimeWait / this.totalEntities;
        
            System.out.printf("│ %-48s : %6s min │\n", "Tiempo maximo de espera", df.format(this.maxTimeWait));
            System.out.printf("│ %-48s : %6s min │\n", "Tiempo minimo de espera", df.format(this.minTimeWait));
            System.out.printf("│ %-48s : %6s min │\n", "Tiempo medio de espera", df.format(tiempoMedioWait));
        } else {
            System.out.printf("│ %-48s : %10s │\n", "Tiempo maximo de espera", "N/A");
            System.out.printf("│ %-48s : %10s │\n", "Tiempo minimo de espera", "N/A");
            System.out.printf("│ %-48s : %10s │\n", "Tiempo medio de espera", "N/A");
        }

        System.out.println("└──────────────────────────────────────────────────────────────┘\n");
    }
}
