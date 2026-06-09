package tarea3.logica;

/**
 * clase que represena el producto del tipo Snicker
 * @author Nicolas Silva
 * @version 1.0, 9 de junio de 2026
 */
class Snickers extends Dulce {
    /**
     * constructor de la clase Snickers
     * @param numSerie será el ID del snicker comprado
     */
    public Snickers(int numSerie) {  //constructor
        super(numSerie);
    }

    /**
     * acción de consumir un snicker
     * @return string "snickers"
     */
    @Override
    public String consumir() {
        return "snickers";
    }
}