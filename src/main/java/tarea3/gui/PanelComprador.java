package tarea3.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import tarea3.logica.Expendedor;

/**
 * Clase que representa la vista y el controlador del comprador en la interfaz grafica.
 * Se encarga de dibujar el panel de la derecha, mostrar el monedero, los botones de seleccion,
 * y capturar las interacciones del usuario mediante clicks del mouse.
 * @author Daniel Lopez
 * @version 1.0, 2 de junio de 2026
 */


public class PanelComprador extends JPanel {
    private Expendedor exp;
    private PanelPrincipal parent;

    /**Constructor de la clase PanelComprador.
     * @param exp el modelo logico de la maquina expendedora.
     * @param parent referencia del panel principal para poder actualizarce en la pantalla.
     */
    public PanelComprador(Expendedor exp, PanelPrincipal parent) {
        this.exp = exp;
        this.parent = parent;
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
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 500, 700);
    }
}
