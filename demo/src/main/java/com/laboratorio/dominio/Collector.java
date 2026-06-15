package com.laboratorio.dominio;

/**
 * Interface para coleccionar estadisticas
 * @author eldem
 */
public interface Collector {
    
    void collect(double time);

    void printReport();

}
