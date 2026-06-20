package com.laboratorio.collectors;
import java.util.List;
import com.laboratorio.collectors.CollectorTimeOnSystem;

public class FinalCollector{
    private int totalEntitiesArrival;
    private int totalEntitiesEoS;
    private double maxTimeToS;
    private double minTimeToS;
    private double totalTimeToS;
    private double totalTimeWait;
    private int totalEntitiesWait;
    private double maxTimeWait;
    private double minTimeWait;
    private double maxSizeQueue;
    private List<Double> totalTimeLeisure;
    private List<Double> minTimeLeisure;
    private List<Double> maxlTimeLeisure;
    private List<Double> finalDurability;

    public void FinalCollector(){
        totalEntitiesArrival = 0;
        totalEntitiesEoS = 0;
        maxTimeToS = Double.MIN_VALUE;
        minTimeToS = Double.MAX_VALUE;
        totalTimeToS = 0;
        totalTimeWait = 0;
        totalEntitiesWait = 0;
        maxTimeWait = Double.MIN_VALUE;
        minTimeWait = Double.MAX_VALUE;
        maxSizeQueue = Double.MIN_VALUE;
    }

    public void collect(CollectorTimeOnSystem cToS, CollectorTimeWait cTW, CollectorSizeQueue cSQ, List<CollectorServerStats> cT){
        this.totalEntitiesArrival += cToS.getTotalEntitiesArrival();
        this.totalEntitiesEoS += cToS.getTotalEntitiesEoS();
        this.maxTimeToS += cToS.getMaxTimeToS();
        this.minTimeToS += cToS.getMinTimeToS();
        this.totalTimeToS += cToS.getTotalTimeToS();

    }
}
