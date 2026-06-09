package tarea3.logica;

/**
 * Objeto que representa un producto del tipo Fanta
 * @author Nicolas Silva
 * @version 1.0, 9 de junio de 2026
 */
class Fanta extends Bebida {
    /**
     * constructor de la clase Fanta
     * @param numSerie será el ID de la Fanta comprada
     */
    public Fanta(int numSerie) {
        super(numSerie);
    }

    /**
     * acción de consumir la Fanta
     * @return string "fanta"
     */
    @Override
    public String consumir() {
        return "fanta";
    }
}