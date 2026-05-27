package com.laboratorio.dominio;

import java.util.List;

public interface Behavior {
    
    double behavior(List<Distribution> distributions, double clock);

}