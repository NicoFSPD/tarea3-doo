package tarea3.logica;

/**
 * Excepcion personalizada que se lanza cuando el deposito de productos esta vacio
 * Se utiliza en el metodo comprarProducto del Expendedor cuando no queda stock
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public class NoHayProductoException extends Exception {
    /**
     * Constructor que permite definir un mensaje personalizado para la excepcion
     * @param message String que describe la causa del error
     */
    public NoHayProductoException(String message) {
        super(message);
    }
}
