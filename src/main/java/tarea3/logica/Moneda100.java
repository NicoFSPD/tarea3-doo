package tarea3.logica;

/**
 * Subclase que representa una moneda con valor de $100.
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public class Moneda100 extends Moneda {
    /**
     * Constructor para crear una Moneda de $100.
     */
    public Moneda100() {
        super();
    }

    /**
     * Obtiene el valor de esta moneda.
     *
     * @return El valor entero 100.
     */
    @Override
    public int getValor() {
        return 100;
    }
}
