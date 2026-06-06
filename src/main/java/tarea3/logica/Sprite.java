package tarea3.logica;

/**
 * Objeto Sprite
 */
class Sprite extends Bebida {

    /**
     * constructor de la clase Sprite
     * * @param numSerie será el ID de la sprite comprada
     */
    public Sprite(int numSerie) {
        super(numSerie);
    }

    /**
     * acción de consumir la sprite
     * * @return string "sprite"
     */
    @Override
    public String consumir() {
        return "sprite";
    }
}