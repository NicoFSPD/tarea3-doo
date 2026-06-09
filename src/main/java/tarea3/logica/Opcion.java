package tarea3.logica;

/**
 * Una enumeracion que le entrega a los productos constantes que los diferencian
 * @author Nicolas Silva
 * @version version 1.1, 9 de junio de 2026
 */
public enum Opcion {
    /** Se definen desde aqui, las constantes*/
    COCA(1,1200),
    SPRITE(2, 1200),
    FANTA(3, 1200),
    SNICKERS(4, 800),
    SUPER8(5, 700);
    /** Dos valores int por producto: Su identificador (ID) y su precio.
     * Son publicas pero su condicion final ya las hace, en parte, no alterables*/
    public final int ID;
    public final int precio;
    /** El constructor le asignara a las variables, las constantes predefinidas*/
    Opcion(int ID, int precio){
        this.ID = ID;
        this.precio = precio;
    }
}