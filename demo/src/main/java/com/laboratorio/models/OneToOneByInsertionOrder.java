package com.laboratorio.models;

import java.util.List;

import com.laboratorio.dominio.ModelSpecificator;
import com.laboratorio.dominio.Queue;
import com.laboratorio.dominio.Server;

/**
 * Modelo de una queue para cada servidor, por orden de insercion
 * 
 */
public class OneToOneByInsertionOrder implements ModelSpecificator {

    @Override
    public void specificate(List<Server> servers, List<Queue> queues) {
        try {
            for(int i = 0; i < servers.size(); i++) {
                servers.get(i).setQueue(queues.get(i));
            }
        }catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Hay más servidores que colas. Faltan colas para asignar.");
        }
    }    
}
