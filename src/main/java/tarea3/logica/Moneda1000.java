package tarea3.logica;

/**
 * Subclase que representa una moneda con valor de $1000.
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public class Moneda1000 extends Moneda {
    /**
     * Constructor para crear una Moneda de $1000.
     */
    public Moneda1000() {
        super();
    }

    /**
     * Obtiene el valor de esta moneda.
     *
     * @return El valor entero 1000.
     */
    @Override
    public int getValor() {
        return 1000;
    }
}