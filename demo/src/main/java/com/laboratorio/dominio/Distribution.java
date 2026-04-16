
package com.laboratorio.dominio;


public interface  Distribution {

    /**
     * This method return a sample from this distribution.
     * Sometimes is the inverse of the cdf and sometimes is a more complex method,
     * but the idea is that this method return a sample from this distribution.
     * @return A sample from this distribution.
     */
    double sample();

}
