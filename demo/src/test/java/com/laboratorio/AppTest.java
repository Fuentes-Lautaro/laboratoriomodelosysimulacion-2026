package com.laboratorio;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.laboratorio.distribution.Normal;
import com.laboratorio.dominio.Distribution;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        double mu = 5.0;
        double stdDev = 1.0;
        int numSamples = 10000;
        Distribution normal = new Normal(mu, stdDev);

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for (int i = 0; i < numSamples; i++) {
            double sample = normal.sample();

            if (mu-stdDev <= sample && sample <= mu+stdDev) 
                count1++;
            
            if (mu-2*stdDev <= sample && sample <= mu+2*stdDev) 
                count2++;

            if (mu-3*stdDev <= sample && sample <= mu+3*stdDev) 
                count3++;
        }

        double proportion1 = (double) count1 / numSamples;
        double proportion2 = (double) count2 / numSamples;
        double proportion3 = (double) count3 / numSamples;

        System.out.println(proportion1);
        System.out.println(proportion2);
        System.out.println(proportion3);

        assertTrue(.67 < proportion1 && proportion1 < 0.69);
        assertTrue(.95 < proportion2 && proportion2 < 0.96);
        assertTrue(.997 < proportion3 && proportion3 < 0.998);
    }
}
