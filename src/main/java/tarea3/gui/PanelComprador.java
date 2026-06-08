package tarea3.gui;

import javax.swing.*;
import java.awt.*;

import tarea3.logica.Comprador;
import tarea3.logica.Expendedor;
import tarea3.logica.Moneda;
import tarea3.logica.Producto;

/**
 * Clase que representa la vista y el controlador del comprador en la interfaz grafica.
 * Se encarga de dibujar el panel de la derecha, mostrar el monedero, los botones de seleccion,
 * y capturar las interacciones del usuario mediante clicks del mouse.
 * @author Daniel Lopez
 * @author Nicolas Silva
 * @version 1.3, 7 de junio de 2026
 */


public class PanelComprador extends JPanel {

    //ATRIBUTOS DEL PANEL
    private Comprador com;
    private PanelMochila panelMochila;
    private PanelMonedero panelMonedero;
    private PanelPrincipal panelPrincipal;
    private int estadoBoton = -1;


    /**Constructor del panel de comprador
     * @param com : Necesita informacion del comprador para representarla
     * @param parent : Comunicacion con el panel principal del cual depende*/
    public PanelComprador(Comprador com, PanelPrincipal parent) {
        this.com = com;
        this.panelPrincipal = parent;

        setLayout(null);
        setBackground(new Color(150,150,150));


        //#################################################################
        //          --- PANEL DE SELECCION DE PRODUCTOS ---
        //#################################################################

        JPanel panelProductos = new JPanel(new FlowLayout());
        panelProductos.setBackground(new Color(0, 180, 180));
        panelProductos.setBounds(10, 10, 510, 120);

        //BOTONES PARA LAS OPCIONES DE PRODUCTO
        JButton btncoca = new JButton("COCA-COLA (1200$)");
        JButton btnsprite = new JButton("SPRITE (1200$)");
        JButton btnfanta = new JButton("FANTA (1200$)");
        JButton btnsnick = new JButton("SNICKERS (800$)");
        JButton btnsu8 = new JButton("SUPER8 (700$)");

        //COLORES CARACTERISTICOS PARA LOS BOTONES
        btncoca.setBackground(new Color(255,0,0));
        btnsprite.setBackground(new Color(0, 255, 11));
        btnfanta.setBackground(new Color(255, 128,0));
        btnsnick.setBackground(new Color(0, 0, 255));
        btnsu8.setBackground(new Color(0, 0, 0));

        //Agregar los botones ya instanciados desde Swing
        panelProductos.add(btncoca);
        panelProductos.add(btnsprite);
        panelProductos.add(btnfanta);
        panelProductos.add(btnsnick);
        panelProductos.add(btnsu8);

        //#################################################################
        //                   --- PANEL PARA ACCIONAR ---
        //#################################################################

        JPanel panelAcciones = new JPanel(new GridLayout(3, 2, 5, 5));
        panelAcciones.setBackground(new Color(0, 160, 160));
        panelAcciones.setBounds(10, 140, 510, 150);

        //BOTONES PARA SELECCION DE MONEDA
        JButton btn100 = new JButton("100$");
        JButton btn500 = new JButton("500$");
        JButton btn1000 = new JButton("1000$");
        JButton btn1500 = new JButton("1500$");

        //listeners
        btn100.addActionListener(e -> ingresarMonedaAlExpendedor(100));
        btn500.addActionListener(e -> ingresarMonedaAlExpendedor(500));
        btn1000.addActionListener(e -> ingresarMonedaAlExpendedor(1000));
        btn1500.addActionListener(e -> ingresarMonedaAlExpendedor(1500));

        //BOTONES PARA ACCIONAR
        JButton btnPagar = new JButton("PAGAR");
        JButton btnVuelto = new JButton("RETIRAR VUELTO");

        btnPagar.setBackground(new Color(40, 230, 230));
        btnVuelto.setBackground(new Color(230, 40, 230));

        //listeners
        btnPagar.addActionListener(e -> {
            if (estadoBoton == -1) {
                System.out.println("Primero debes seleccionar un producto");
                return;
            }
            try {
                panelPrincipal.getExpendedor().comprarProducto(estadoBoton);
                System.out.println("Compra procesada en la maquina");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            estadoBoton = -1;
            actualizar();
        });

        btnVuelto.addActionListener(e -> {
            com.recogerVueltoDeMaquina(panelPrincipal.getExpendedor());
            actualizar();
        });

        //Agregar los botones ya instanciados desde Swing
        panelAcciones.add(btn100);
        panelAcciones.add(btn500);
        panelAcciones.add(btn1000);
        panelAcciones.add(btn1500);
        panelAcciones.add(btnPagar);
        panelAcciones.add(btnVuelto);


        //#################################################################
        // --- PANELES DE MONEDERO Y PRODUCTOS ADQUIRIDOS (MOCHILA) ---
        //#################################################################

        panelMonedero = new PanelMonedero(com);
        panelMonedero.setBounds(10, 300, 510, 190);

        panelMochila = new PanelMochila(com);
        panelMochila.setBounds(10, 500, 510, 210);

        //BLOQUE FINAL DE AGREGADO DE TODOS LOS PANELES (DE COMPOSICION).
        add(panelProductos);
        add(panelAcciones);
        add(panelMonedero);
        add(panelMochila);

    }

    /**
     * Refresca el renderizado de los paneles de representacion dinamica
     * y propaga la actualizacion visual hacia el panel principal.
     * Debe invocarse cada vez que el estado del comprador o del expendedor cambie.
     */
    public void actualizar(){
        panelMonedero.repaint();
        panelMochila.repaint();
        panelPrincipal.repaint();
    }

    /**
     * Remueve una moneda del monedero del comprador y la transfiere
     * al deposito de monedas en espera de la maquina expendedora.
     */
    private void ingresarMonedaAlExpendedor(int valor) {
        for (int i = 0; i < com.getMonedero().size(); i++) {
            if (com.getMonedero().get(i).getValor() == valor) {
                Moneda m = com.getMonedero().remove(i);
                panelPrincipal.getExpendedor().recibirMonedaEnEspera(m);
                actualizar();
                return;
            }
        }
        System.out.println("No tienes monedas de $" + valor);
    }

    /**Procesa y evalua los clicks del mouse realizados dentro de la region del comprador.
     * Permite al comprador seleccionar productos, escoger monedas para el pago o retirar productos.
     * @param x coordenada horizontal.
     * @param y coordenada vertical.
     */
    public void manejarClick(int x, int y) {
    }

    /**Realiza el renderizado y dibujo de los componentes visuales del sector del comprador.
     * Dibuja el fondo cyan.
     * @param g el objeto graphics utilizado para pintar en el componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawString("PRODUCTOS", 10, 8);
        g.drawString("FORMA DE PAGO Y RETIRO DE VUELTO", 10, 138);
        g.drawString("MONEDERO", 10, 298);
        g.drawString("MOCHILA", 10, 498);
    }
}
