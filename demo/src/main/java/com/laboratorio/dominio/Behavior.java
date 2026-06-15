package com.laboratorio.dominio;

import java.util.List;

/**
 * Interface para implementar decisiones de distribucion.
 * @author eldem
 */
public interface Behavior {
    
    double behavior(List<Distribution> distributions, double clock);

}