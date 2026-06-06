package tarea3.logica;

/**
 * Excepcion personalizada que se lanza cuando se trata de comprar algo con menos dinero de lo que cuesta
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public class PagoInsuficienteException extends Exception {
    /**
     * Constructor que permite definir un mensaje personalizado para la excepcion
     * @param message String que describe la causa del error
     */
    public PagoInsuficienteException(String message) {
        super(message);
    }
}