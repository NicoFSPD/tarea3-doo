package tarea3.logica;

/**
 * Subclase que representa una moneda con valor de $500.
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public class Moneda500 extends Moneda {
    /**
     * Constructor para crear una Moneda de $500.
     */
    public Moneda500() {
        super();
    }

    /**
     * Obtiene el valor de esta moneda.
     *
     * @return El valor entero 500.
     */
    @Override
    public int getValor() {
        return 500;
    }
}
