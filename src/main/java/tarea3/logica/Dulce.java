package tarea3.logica;


/**
 * clase abstracta que de los productos de tipo dulce
 * es heredada de la super clase producto
 * @author Nicolas Silva
 * @version 1.0, 9 de junio de 2026
 */
abstract class Dulce extends Producto {         //subclase para las DULCES
    /**
     * constructor de la clase Dulce
     *@param numSerie será el ID del dulce
     */
    public Dulce(int numSerie) {
        super(numSerie);
    }
}