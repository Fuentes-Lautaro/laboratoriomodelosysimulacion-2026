package com.laboratorio.collectors;
import com.laboratorio.dominio.Collector;

public class CollectorServerStats implements Collector{
    private double totalTimeLeisure;
    private double minTimeLeisure;
    private double maxTimeLeisure;
    private double finaldurability;

    public CollectorServerStats() {
        this.totalTimeLeisure = 0.0;
        this.minTimeLeisure = Double.MAX_VALUE;
        this.maxTimeLeisure = Double.MIN_VALUE;
        this.finaldurability = 0;
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
        return this.totalTimeLeisure;
    }

    public double getAverageTimeLeisure(double timeSim) {
        return (this.totalTimeLeisure / timeSim) * 100;
    }

    public double getMinTimeLeisure() {
        return this.minTimeLeisure;
    }

    public double getMaxTimeLeisure() {
        return this.maxTimeLeisure;
    }

    public double getFinalDurability(){
        return this.finaldurability;
    }

    @Override
    public void printReport() {
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
