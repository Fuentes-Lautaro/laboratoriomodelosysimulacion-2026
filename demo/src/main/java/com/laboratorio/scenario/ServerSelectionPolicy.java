package com.laboratorio.scenario;

import java.util.List;

import com.laboratorio.dominio.Server;

@FunctionalInterface
public interface ServerSelectionPolicy {

    public Server selectServer(List<Server> servers);

}
