package tarea3.logica;


/**
 * Clase abstracta de objeto que puede tomarse como bebida o dulce y sus respectivos tipos
 * Define la propiedad serie y metodos que tendrán luego los productos disponibles.
 * @author Nicolas Silva
 * @version 1.0, 30 de abril 2026
 */
public abstract class Producto{
    private int serie;

    /**
     * Constructor de la clase producto.
     * * @param numSerie será el ID del producto en cuestión.
     */
    public Producto(int numSerie){
        this.serie = numSerie;
    }
    /**
     * Getter del ID del producto.
     * * @return numSerie será el ID del producto en cuestión.
     */
    public int getSerie(){
        return this.serie;
    }

    /**
     * Metodo abstracto que "realiza la acción" de comer/beber el producto.
     * * @return retorna un string con el sonido o el nombre del producto consumido.
     */
    public abstract String consumir();
}