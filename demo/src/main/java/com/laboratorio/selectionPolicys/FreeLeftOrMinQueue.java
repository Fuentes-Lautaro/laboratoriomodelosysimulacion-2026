package com.laboratorio.selectionPolicys;

import java.util.List;

import com.laboratorio.dominio.Server;
import com.laboratorio.dominio.ServerSelectionPolicy;

public class FreeLeftOrMinQueue implements ServerSelectionPolicy{

    @Override
    public Server selectServer(List<Server> servers) {
        
        for (Server server : servers) {
            if (!server.isBusy()) {
                return server; 
            }
        }

        Server bestBusy = servers.get(0);
        int minSizeQueue = bestBusy.getQueue().size();

        for (int i = 1; i < servers.size(); i++) {
            Server s = servers.get(i);
            int sizeQueue = s.getQueue().size();
                if (sizeQueue < minSizeQueue) {
                    minSizeQueue = sizeQueue;
                    bestBusy = s;
                }
        }

        return bestBusy;
    }
}