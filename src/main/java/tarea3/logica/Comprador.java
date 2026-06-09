package tarea3.logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa a un comprador de productos de un expendedor.
 * Crea el monedero, una mochila para los productos adquiridos,
 * e interactua con la maquina mediante metodos de seleccion, pago y retiro.
 * @author Daniel Lopez
 * @author Eduardo Riveros
 * @author Nicolas Silva
 * @version 1.3, 9 de junio de 2026
 */
public class Comprador {
    private ArrayList<Moneda> monedero;
    private ArrayList<Producto> mochila;
    private String sonido;

    /**
     * Constructor que inicializa al comprador con monedas suficientes
     * y compartimientos vacios para empezar a interactuar.
     */
    public Comprador() {
        this.monedero = new ArrayList<>();
        this.mochila = new ArrayList<>();
        this.sonido = null;

        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda100());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda500());
        this.monedero.add(new Moneda1000());
        this.monedero.add(new Moneda1000());
        this.monedero.add(new Moneda1000());
        this.monedero.add(new Moneda1000());
        this.monedero.add(new Moneda1500());
        this.monedero.add(new Moneda1500());
        this.monedero.add(new Moneda1500());
        this.monedero.add(new Moneda1500());


        Collections.sort(this.monedero);
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
        Collections.sort(this.monedero);
    }
    /**
     * Getters necesarios para que el PanelComprador puedo dibujar los
     * objetos en la pantalla.
     * @return
     */
    public ArrayList<Moneda> getMonedero() { return this.monedero; }
    public ArrayList<Producto> getMochila() { return this.mochila; }
}
