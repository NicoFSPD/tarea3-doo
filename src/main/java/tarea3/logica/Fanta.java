package tarea3.logica;

/**
 * Objeto Fanta
 */
class Fanta extends Bebida {
    /**
     * constructor de la clase Fanta
     * * @param numSerie será el ID de la Fanta comprada
     */
    public Fanta(int numSerie) {
        super(numSerie);
    }

    /**
     * acción de consumir la Fanta
     * * @return string "fanta"
     */
    @Override
    public String consumir() {
        return "fanta";
    }
}