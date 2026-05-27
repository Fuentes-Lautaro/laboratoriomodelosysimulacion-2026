package com.laboratorio.dominio;

import java.util.List;

@FunctionalInterface
public interface ServerSelectionPolicy {

    public Server selectServer(List<Server> servers);

}
