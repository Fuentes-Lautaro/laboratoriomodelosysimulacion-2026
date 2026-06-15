
package com.laboratorio.dominio;


public interface  Distribution {

    /**
     * Interface para aplicar distintas distribuciones para crear variables aleatorias.
     * @return A sample from this distribution.
     */
    double sample();

}
