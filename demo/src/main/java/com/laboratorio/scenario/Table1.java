/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.laboratorio.scenario;

import com.laboratorio.dominio.Distribution;
import com.laboratorio.dominio.Randomizer;

/**
 *
 * @author eldem
 */
public class Table1 implements Distribution {

    @Override
    public double sample() {
        double r = Randomizer.nroRandom();
        if (r < 0.35) return 10d;
        if (r < 0.8) return 15d;
        return 17d;
    }
}
