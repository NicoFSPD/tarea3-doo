package tarea3.gui;

import javax.swing.*;
import java.awt.*;

import tarea3.logica.Comprador;
import tarea3.logica.Expendedor;

/**
 * Clase que representa la vista y el controlador del comprador en la interfaz grafica.
 * Se encarga de dibujar el panel de la derecha, mostrar el monedero, los botones de seleccion,
 * y capturar las interacciones del usuario mediante clicks del mouse.
 * @author Daniel Lopez
 * @version 1.1, 2 de junio de 2026
 */


public class PanelComprador extends JPanel {

    private Expendedor exp;
    private Comprador com;
    private PanelMochila panelMochila;
    private PanelMonedero panelMonedero;
    private PanelPrincipal panelPrincipal;


    public PanelComprador(Comprador com, Expendedor exp, PanelPrincipal parent) {
        this.exp = exp;
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
        javax.swing.JButton btncoca = new javax.swing.JButton("COCA-COLA (1200$)");
        javax.swing.JButton btnsprite = new javax.swing.JButton("SPRITE (1200$)");
        javax.swing.JButton btnfanta = new javax.swing.JButton("FANTA (1200$)");
        javax.swing.JButton btnsnick = new javax.swing.JButton("SNICKERS (800$)");
        javax.swing.JButton btnsu8 = new javax.swing.JButton("SUPER8 (700$)");

        btncoca.setBackground(new Color(255,0,0));
        btnsprite.setBackground(new Color(0, 255, 11));
        btnfanta.setBackground(new Color(255, 128,0));
        btnsnick.setBackground(new Color(0, 0, 255));
        btnsu8.setBackground(new Color(0, 0, 0));

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
        javax.swing.JButton btn100 = new javax.swing.JButton("100$");
        javax.swing.JButton btn500 = new javax.swing.JButton("500$");
        javax.swing.JButton btn1000 = new javax.swing.JButton("1000$");
        javax.swing.JButton btn1500 = new javax.swing.JButton("1500$");

        //BOTONES PARA ACCIONAR
        javax.swing.JButton btnPagar = new javax.swing.JButton("PAGAR");
        javax.swing.JButton btnVuelto = new javax.swing.JButton("RETIRAR VUELTO");

        btnPagar.setBackground(new Color(40, 230, 230));
        btnVuelto.setBackground(new Color(230, 40, 230));

        panelAcciones.add(btn100);
        panelAcciones.add(btn500);
        panelAcciones.add(btn1000);
        panelAcciones.add(btn1500);
        panelAcciones.add(btnPagar);
        panelAcciones.add(btnVuelto);



        add(panelProductos);
        add(panelAcciones);

    }

    public void actualizar(){
        panelPrincipal.repaint();
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
    }
}
