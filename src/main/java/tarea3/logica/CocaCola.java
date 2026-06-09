package tarea3.logica;

/**
 * Objeto CocaCola
 * @author Nicolas Silva
 * @version 1.0, 9 de junio de 2026
 **/
class CocaCola extends Bebida {
    /**
     * constructor de la clase CocaCola
     * @param numSerie será el ID de la cocacola comprada
     */
    public CocaCola(int numSerie) {
        super(numSerie);
    }
    /**
     * acción de consumir la cocacola
     * @return string "cocacola"
     */
    @Override
    public String consumir() {
        return "cocacola";
    }
}