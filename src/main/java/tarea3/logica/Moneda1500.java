package tarea3.logica;

/**
 * Subclase que representa una moneda con valor de $1500.
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public class Moneda1500 extends Moneda {
    /**
     * Constructor para crear una Moneda de $1500.
     */
    public Moneda1500() {
        super();
    }

    /**
     * Obtiene el valor de esta moneda.
     *
     * @return El valor entero 1500.
     */
    @Override
    public int getValor() {
        return 1500;
    }
}
