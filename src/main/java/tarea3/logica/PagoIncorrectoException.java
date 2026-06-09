package tarea3.logica;

/**
 * Excepcion personalizada que se lanza cuando se trata de comprar con una moneda null
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public class PagoIncorrectoException extends Exception {
    /**
     * Constructor que permite definir un mensaje personalizado para la excepcion
     * @param message String que describe la causa del error
     */
    public PagoIncorrectoException(String message) {
        super(message);
    }
}
