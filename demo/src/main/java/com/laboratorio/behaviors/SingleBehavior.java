package com.laboratorio.behaviors;

import java.util.List;

import com.laboratorio.dominio.Behavior;
import com.laboratorio.dominio.Distribution;

public class SingleBehavior implements Behavior{

    private final int position;

    public SingleBehavior(int position){
        this.position = position;
    }

    @Override
    public double behavior(List<Distribution> distributions, double clock) {
        return distributions.get(this.position).sample();
    }

}
