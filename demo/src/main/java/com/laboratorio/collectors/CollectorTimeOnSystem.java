package com.laboratorio.collectors;
import com.laboratorio.dominio.Collector;

/**
 * Colecciona estadisticas sobre cantidad de entidades que arriban y salen del sistema, y su tiempo en el sistema.
 * @author eldem
 */
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
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");

        System.out.println("\n┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                     REPORTE DEL SISTEMA                      │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-45s : %6d │\n", "Total de entidades que arribaron", this.totalEntitiesArrival);
        System.out.printf("│ %-45s : %6d │\n", "Total de entidades que salieron (EoS)", this.totalEntitiesEoS);
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.println("│               ESTADÍSTICAS DE TIEMPO EN SISTEMA              │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-45s : %6s min │\n", "Tiempo máximo registrado", df.format(this.maxTime));
        System.out.printf("│ %-45s : %6s min │\n", "Tiempo mínimo registrado", df.format(this.minTime));
    
        if (this.totalEntitiesEoS > 0) {
            double tiempoMedio = (double) this.totalTime / this.totalEntitiesEoS;
            System.out.printf("│ %-45s : %6s min │\n", "Tiempo medio en el sistema", df.format(tiempoMedio));
        } else {
            System.out.printf("│ %-45s : %10s │\n", "Tiempo medio en el sistema", "N/A (Sin salidas)");
        }
        System.out.println("└──────────────────────────────────────────────────────────────┘\n");
    }
}