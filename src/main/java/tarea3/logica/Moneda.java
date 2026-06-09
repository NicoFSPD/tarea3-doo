package tarea3.logica;

/**
 * Clase abstracta que representa una Moneda en el sistema del expendedor.
 * Implementa la interfaz Comparable para permitir el ordenamiento de monedas por su valor.
 * @author Daniel Lopez
 * @version 1, 30 de abril de 2026
 */
public abstract class Moneda implements Comparable<Moneda> {
    /**
     * Constructor por defecto de la clase Moneda.
     */
    public Moneda() {
    }

    /**
     * Obtiene la referencia de la serie de la moneda.
     * @return La instancia actual de la moneda.
     */
    public Moneda getSerie() {
        return this;
    }

    /**
     * Metodo abstracto para obtener el valor de la moneda.
     *
     * @return El valor de la moneda como un entero.
     */
    public abstract int getValor();

    /**
     * Compara esta moneda con otra basandose en su valor.
     * Requiere a la interfaz Comparable para el uso de metodos de ordenamiento.
     * @param otraMoneda La moneda con la que se va a comparar.
     * @return Un valor negativo, cero o positivo si esta moneda es menor, igual o mayor que la moneda comparada.
     */
    @Override
    public int compareTo(Moneda otraMoneda) {
        return Integer.compare(this.getValor(), otraMoneda.getValor());
    }

    /**
     * Devuelve una representacion en cadena de la moneda, incluyendo su valor y serie.
     * @return String con la descripcion de la moneda.
     */
    @Override
    public String toString() {
        return "Moneda de " + this.getValor() + " (Serie: " + this.hashCode() + ")";
    }
}

