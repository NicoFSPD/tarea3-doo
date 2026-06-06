package org.example;

public class DepositoObstruidoException extends Exception {  //excepcion para cuando se quiere comprar pero el deposito de retiro tiene algo si reirar
    public DepositoObstruidoException(String message) {
        super(message);

    }
}