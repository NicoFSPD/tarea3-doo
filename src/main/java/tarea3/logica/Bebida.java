package tarea3.logica;

/**
 * Clase abstracta que de los productos de tipo bebida
 * es heredada de la super clase producto
 * @author Nicolas Silva.
 * @version 1.0, 9 de junio de 2026.
 */
abstract class Bebida extends Producto {
    /**
     * Constructor de la clase Bebida
     * @param numSerie será el ID de la bebida
     */
    public Bebida(int numSerie) {
        super(numSerie);
    }
}