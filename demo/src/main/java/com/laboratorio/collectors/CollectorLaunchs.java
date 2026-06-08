package com.laboratorio.collectors;

import java.util.ArrayList;
import java.util.List;

import com.laboratorio.dominio.Collector;

public class CollectorLaunchs implements Collector{
    private List<Double> arrivals = new ArrayList();
    private List<Double> eoSs = new ArrayList();
    private List<Double> averageToS = new ArrayList();
    private List<Double> maxToS = new ArrayList();
    private List<Double> minToS = new ArrayList();
    private List<Double> AverageToS = new ArrayList();

    @Override
    public void collect(double time){

    }

    public void collectLaunchs(){

    }

    @Override
    public void printReport(){

    }
}
