package com.laboratorio.intervals;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import com.laboratorio.dominio.Interval;

public class Intervals implements Interval {
    private double lu;
    private double ld;
    private double marginOfError;

    public Intervals(){
        this.lu = 0;
        this.ld = 0;
    }

    @Override
    public void calculateInterval(List<Double> values, Double z){
        double stdev = 0, mean = 0;

        for (Double d : values){
            mean += d;
        }

        mean = mean/values.size();

        for (Double d : values){
            stdev += Math.pow((d - mean), 2);
        } 

        stdev = stdev / (values.size() - 1);

        stdev = Math.sqrt(stdev);

        marginOfError = z * (stdev / Math.sqrt(values.size()));

        this.lu = mean + marginOfError;
        this.ld = mean - marginOfError;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.US));
        return "(" + df.format(this.ld) + ", " + df.format(this.lu) + ")";
    }
}
