
package com.laboratorio.dominio;

public interface  Queue {

    /**
     * 
     * 
     * @return SI CLARO
     */
    Entity next();

    /**
     * 
     * @param e
     */
    void enqueue(Entity e); //PONE EN COLA A LA ENTIDAD

    /**
     * 
     * @return
     */
    int size();

    /**
     * 
     * @return
     */
    Entity peek(); //ver la cabeza sin destruir

}
