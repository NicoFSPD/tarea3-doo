package tarea3.logica;

/**
 * clase abstracta que de los productos de tipo bebida
 * es heredada de la super clase producto
 * @author Nicolas Silva.
 * @version 1.0, 9 de junio de 2026.
 */
abstract class Bebida extends Producto {        //subclase para las BEBIDAS
    /**
     * constructor de la clase Bebida
     * * @param numSerie será el ID de la bebida
     */
    public Bebida(int numSerie) {
        super(numSerie);
    }
}