package tarea3.logica;

/**
 * Objeto Snicker
 */
class Snickers extends Dulce {
    /**
     * constructor de la clase Snickers
     * * @param numSerie será el ID del snicker comprado
     */
    public Snickers(int numSerie) {  //constructor
        super(numSerie);
    }

    /**
     * acción de consumir un snicker
     * * @return string "snickers"
     */
    @Override
    public String consumir() {
        return "snickers";
    }
}