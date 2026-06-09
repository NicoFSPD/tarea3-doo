package tarea3.logica;

/**
 * Excepción que se lanza cuando se realiza una compra pero el depósito
 * de retiro ya contiene un producto que no ha sido retirado de una compra
 * anterior.
 * @author Eduardo Riveros
 * @version 1.0, 6 de junio de 2026
 */
public class DepositoObstruidoException extends Exception {  //excepcion para cuando se quiere comprar pero el deposito de retiro tiene algo si reirar
    /**
     * Constructor de la excepción.
     * @param message Mensaje detallado que explica la causa del error.
     */
    public DepositoObstruidoException(String message) {
        super(message);

    }
}