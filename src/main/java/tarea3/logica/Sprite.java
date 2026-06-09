package tarea3.logica;

/**
 * CLase del objeto que representa un producto del tipo Sprite.
 * @author Nicolas Silva.
 * @version 1.0, 9 de junio de 2026.
 */
class Sprite extends Bebida {

    /**
     * constructor de la clase Sprite
     * @param numSerie será el ID de la sprite comprada
     */
    public Sprite(int numSerie) {
        super(numSerie);
    }

    /**
     * acción de consumir la sprite
     * @return string "sprite"
     */
    @Override
    public String consumir() {
        return "sprite";
    }
}