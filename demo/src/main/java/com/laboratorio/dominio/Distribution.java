
package com.laboratorio.dominio;


public interface  Distribution {

    /**
     * The cdf inverse function.
     * 
     * @param cumulativeProbability Emulate this with a random.
     * @return A sample from this distribution.
     */
    double sample(double cumulativeProbability); //

}
