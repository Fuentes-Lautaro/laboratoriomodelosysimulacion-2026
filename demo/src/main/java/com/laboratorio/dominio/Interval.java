package com.laboratorio.dominio;
import java.util.List;

public interface Interval {

    void calculateInterval(List<Double> values, Double z);

    String toString();

}
