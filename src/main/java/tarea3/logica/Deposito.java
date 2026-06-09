package tarea3.logica;

import java.util.ArrayList;

/**
 * Clase generica que representa un deposito para almacenar elementos de tipo T
 * Se utiliza para gestionar el almacenamiento de productos o monedas en el expendedor
 * @author Daniel Lopez
 * @version 1.1, 6 de junio de 2026
 * @param <T> El tipo de elemento que almacenara el deposito
 */
public class Deposito<T> {
    /**
     * Lista interna para almacenar los elementos de forma ordenada.
     */
    private ArrayList<T> al;

    /**
     * Constructor que inicializa un deposito vacio
     */
    public Deposito() {
        al = new ArrayList<>();
    }

    /**
     * Agrega un elemento al final del deposito
     * @param x Elemento de tipo T que se desea agregar
     */
    public void addElemento(T x) {
        al.add(x);
    }

    /**
     * Extrae y retorna el primer elemento del deposito (FIFO)
     * @return El elemento extraido de tipo T
     * @throws NoHayProductoException Si el deposito se encuentra vacio al intentar extraer
     */
    public T getElemento() throws NoHayProductoException {
        if (al.size() == 0) {
            throw new NoHayProductoException("Actualmente no hay ningun producto en el deposito.");
        } else {
            return al.remove(0);
        }
    }

    /**
     * Retorna la cantidad de elementos actuales en el deposito.
     * @return entero con el tamaño de la lista.
     */
    public int getCantidad() {
        return this.al.size();
    }

    /**
     * Permite inspeccionar un elemento según su posicion sin removerlo del deposito.
     * @param i indice del elemento a consultar.
     * @return El elemento en la posicion indicada.
     */
    public T getElementoPorIndice(int i) {
        return al.get(i);
    }
}
