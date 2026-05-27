package com.laboratorio.scenario;

import java.util.List;

import com.laboratorio.dominio.Server;
import com.laboratorio.dominio.ServerSelectionPolicy;

public class SelectionPolicy implements ServerSelectionPolicy{

    @Override
    public Server selectServer(List<Server> servers) {
        Server selected = null;
        int minQueueSize = Integer.MAX_VALUE;
        for (Server s : servers) {

            if (!s.isBusy()) {
                return s;
            }
            else if (s.getQueue().size() < minQueueSize) {
                minQueueSize = s.getQueue().size();
                selected = s;
            }
        }
        return selected;
    }

}
