package com.laboratorio.scenario;

import java.util.List;
import java.util.ArrayList;
import com.laboratorio.dominio.Entity;
import com.laboratorio.dominio.Queue;

public final class MyQueue implements Queue {

    private List<Entity> queue;

    public MyQueue() {
        this.queue = new ArrayList<>();
    }

    @Override
    public Entity next() {
        return this.queue.remove(0);
    }

    @Override
    public void enqueue(Entity e) {
        this.queue.add(e);
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public Entity peek() {
        return this.queue.get(0);
    }
}
