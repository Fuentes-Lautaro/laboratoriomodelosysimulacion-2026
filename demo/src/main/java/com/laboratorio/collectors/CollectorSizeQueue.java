package com.laboratorio.collectors;
import com.laboratorio.dominio.Collector;

/**
 * Colecciona estadisticas del tamanio de las colas.
 * @author eldem
 */
public class CollectorSizeQueue implements Collector{
    private double maxSizeQueue;

    public CollectorSizeQueue() {
        this.maxSizeQueue = Double.MIN_VALUE;
    }

    public double getMaxSizeQueue(){
        return this.maxSizeQueue;
    }

    @Override
    public void collect(double sizeQueue) {
        if (sizeQueue > this.maxSizeQueue) {
            this.maxSizeQueue = sizeQueue;
        }
    }

    @Override
    public void printReport() {
        System.out.println("\n┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                 REPORTE DE TAMANO DE COLA                    │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-48s : %10d │\n", "Tamano maximo alcanzado en cola", (int) this.maxSizeQueue);
        System.out.println("└──────────────────────────────────────────────────────────────┘\n");
    }
}
