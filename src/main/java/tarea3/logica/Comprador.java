package org.example;
/**
 * Clase que representa a un comprador de productos de un expendedor
 * El comprador interactua con el expendedor usando una moneda, consume el
 * producto obtenido y luego recibe vuelto
 * @author Daniel Lopez
 * @author Eduardo Riveros
 * @author Nicolas Silva
 * @version 1, 30 de abril de 2026
 */
public class Comprador{
    /**
     * Variable sonido para obtener el sonido que produce el producto consumido
     */
    private String sonido;
    /**
     * Variable vuelto que se usa para obtener el valor del dinero restante recuperado como vuelto
     */
    private int vuelto;
    /**
     * Constructor que realiza la operacion de compra, consumo y recoleccion de vuelto
     * Intenta comprar un producto, si tiene exito, lo consume
     * Retira todas las monedas de vuelto disponibles en el expendedor
     * @param m Moneda utilizada para realizar el pago
     * @param cualProducto entero que identifica el tipo de producto solicitado
     * @param exp referencia al expendedor donde se realiza la compra
     */
    public Comprador(Moneda m, int cualProducto, Expendedor exp) {
        this.vuelto = 0;
        this.sonido = null;

        try {
            /**
             * Intento de compra en el expendedor
             */
            //en esta seccion de codigo antes daba error, ahora quedó solucionado
            exp.comprarProducto(m, cualProducto);
            //-----------------------------------------------------------
            Producto p = exp.getProducto();
            if (p != null) {
                this.sonido = p.consumir();
            }
            /**
             * Bloque catch para capturar excepciones
             */
        } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            System.out.println("error, no se pudo realizar la compra: " + e.getMessage());
            /**
             * Recuperacion de todas las monedas del deposito en el vuelto
             */
        } catch (DepositoObstruidoException e) {
            throw new RuntimeException(e);      //se le agrego la recepcion de esta excepcion, lo pedia
            //intellij al compilar.
        } finally {
            boolean hayVuelto = true;
            while (hayVuelto) {
                try {
                    Moneda monedaVuelto = exp.getVuelto();
                    this.vuelto += monedaVuelto.getValor();
                } catch (NoHayProductoException ex) {
                    hayVuelto = false;
                }
            }
        }
    }

    /**
     * Metodo para obtener el valor total del vuelto
     * @return suma de los valores de todas las monedas del vuelto
     */

    public int cuantoVuelto(){
        return this.vuelto;
    }

    /**
     * Metodo para obtener la descripcion del consumo realizado
     * @return string con el sonido del producto, o null si no hubo consumo
     */
    public String queConsumiste(){
        return this.sonido;
    }
}