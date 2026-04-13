
package com.laboratorio.dominio;

public interface  Queue {

    Entity next(); //TRAIGO LA CABEZA Y DESTRUYO
    void enqueue(Entity e); //PONE EN COLA A LA ENTIDAD
    int size();
    Entity peek(); //ver la cabeza sin destruir

}
