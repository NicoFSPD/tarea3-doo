package tarea3.logica;

/**
 * CLase del Objeto que representa el producto del tipo Super8
 * @author Nicolas Silva
 * @version 1.0, 9 de junio de 2026
 */
class Super8 extends Dulce {
    /**
     * constructor de la clase Super8
     * @param numSerie será el ID del super8 comprado
     */
    public Super8(int numSerie) {    //constructor
        super(numSerie);
    }

    /**
     * acción de consumir la cocacola
     * @return string "cocacola"
     */
    @Override
    public String consumir() {
        return "super8";
    }
}