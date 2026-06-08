package tarea3.logica;

import java.util.ArrayList;

/**
 * Clase que representa a un comprador de productos de un expendedor.
 * Crea el monedero, una mochila para los productos adquiridos,
 * e interactua con la maquina mediante metodos de seleccion, pago y retiro.
 * @author Daniel Lopez
 * @author Eduardo Riveros
 * @author Nicolas Silva
 * @version 1.2, 6 de junio de 2026
 */
public class Comprador {
    private ArrayList<Moneda> monedero;
    private ArrayList<Producto> mochila;
    private String sonido;

    /**
     * Constructor que inicializa al comprador con fondos suficientes
     * y compartimientos vacios para empezar a interactuar.
     */
    public Comprador() {
        this.monedero = new ArrayList<>();
        this.mochila = new ArrayList<>();
        this.sonido = null;

        this.monedero.add(new Moneda1000());
        this.monedero.add(new Moneda1000());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
    }

    /**
     * Extrae una moneda del monedero del comprador para efectuar un pago.
     * @return La moneda seleccionada para pagar, o null si no tiene dinero.
     */
    public Moneda seleccionarMoneda() {
        if (!monedero.isEmpty()) {
            return monedero.remove(0);
        }
        return null;
    }

    /**
     * Ejecuta la orden de compra en el expendedor utilizando el saldo acumulado
     * previamente en el deposito de espera de la maquina.
     * @param cualProducto entero que identifica la opcion o estante del producto solicitado.
     * @param exp referencia al expendedor donde se realiza la compra.
     */
    public void intentarCompra(int cualProducto, Expendedor exp) {
        try {
            exp.comprarProducto(cualProducto);
            System.out.println("Compra procesada con exito.");

        } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException | DepositoObstruidoException e) {
            System.out.println("No se pudo realizar la compra: " + e.getMessage());
        }
    }

    /**
     * Saca el producto del deposito de salida del expendedor y lo almacena en la mochila.
     * Al guardarlo, el comprador consume el producto y almacena su sonido.
     * @param exp referencia al expendedor.
     */
    public void retirarProductoDeMaquina(Expendedor exp) {
        Producto p = exp.getProducto();
        if (p != null) {
            this.mochila.add(p);
            this.sonido = p.consumir();
            System.out.println("Retiraste el producto: " + this.sonido);
        } else {
            System.out.println("El deposito de retiro esta vacio.");
        }
    }

    /**
     * Retira secuencialmente todas las monedas disponibles en el deposito de vuelto
     * del expendedor y las guarda en el monedero personal.
     * @param exp referencia al expendedor.
     */
    public void recogerVueltoDeMaquina(Expendedor exp) {
        boolean hayVuelto = true;
        while (hayVuelto) {
            try {
                Moneda monedaVuelto = exp.getVuelto();
                this.monedero.add(monedaVuelto);
            } catch (NoHayProductoException ex) {
                hayVuelto = false;
            }
        }
    }

    /**
     * Permite al usuario generar dinero de forma manual, "Monedero infinito eventualmente".
     * @param m La moneda magica creada desde la interfaz grafica.
     */
    public void recargarMonedero(Moneda m) {
        if (m != null) {
            this.monedero.add(m);
        }
    }

    /**
     * Metodo para obtener el valor total de dinero disponible en el monedero.
     * @return suma de los valores de todas las monedas guardadas.
     */
    public int cuantoDineroTienes() {
        int total = 0;
        for (Moneda m : monedero) {
            total += m.getValor();
        }
        return total;
    }

    /**
     * Metodo para obtener la descripcion del ultimo consumo realizado.
     * @return string con el sonido del producto, o null si no ha consumido nada.
     */
    public String queConsumiste() {
        return this.sonido;
    }

    /**
     * Getters necesarios para que el PanelComprador puedo dibujar los
     * objetos en pantalla.
     * @return
     */
    public ArrayList<Moneda> getMonedero() { return this.monedero; }
    public ArrayList<Producto> getMochila() { return this.mochila; }
}
