package com.laboratorio.models;

import java.util.List;

import com.laboratorio.dominio.ModelSpecificator;
import com.laboratorio.dominio.Queue;
import com.laboratorio.dominio.Server;

public class OneQueueToNServers implements ModelSpecificator{

    @Override
    public void specificate(List<Server> servers, List<Queue> queues) {
        for (int i=0; i<servers.size(); i++){
            servers.get(i).setQueue(queues.get(0));
        }
    }
}