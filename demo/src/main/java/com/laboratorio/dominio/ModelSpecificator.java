package com.laboratorio.dominio;

import java.util.List;

public interface ModelSpecificator {
    
    /**
     * Especificador del modelo que se va a usar para aplicar las colas a los servidores.
     * @param servers
     * @param queues
     */
    void specificate(List<Server> servers, List<Queue> queues);

}
