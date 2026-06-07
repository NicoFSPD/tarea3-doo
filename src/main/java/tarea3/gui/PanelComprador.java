package tarea3.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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
    private PanelPrincipal parent;

    // Objetos Image para almacenar las texturas cargadas
    private Image imgCoca;
    private Image imgSprite;
    private Image imgFanta;
    private Image imgSnickers;
    private Image imgSuper8;
    // ATRIBUTOS NUEVOS PARA LAS MONEDAS
    private Image imgM100;
    private Image imgM500;
    private Image imgM1000;
    private Image imgM1500;

    /**Constructor de la clase PanelComprador.
     * @param exp el modelo logico de la maquina expendedora.
     * @param parent referencia del panel principal para poder actualizarce en la pantalla.
     */
    public PanelComprador(Expendedor exp, PanelPrincipal parent) {
        this.exp = exp;
        this.parent = parent;
    }

    /**
     * Carga de forma segura los archivos de imagen desde la carpeta de recursos de Maven.
     */
    private void cargarImagenes() {
        try {
            imgCoca = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cocacola.png"))).getImage();
            imgSprite = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/sprite.png"))).getImage();
            imgFanta = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/fanta.png"))).getImage();
            imgSnickers = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/snickers.png"))).getImage();
            imgSuper8 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/super8.png"))).getImage();
            // CARGA DE LAS NUEVAS TEXTURAS DE MONEDAS
            imgM100 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda100.png"))).getImage();
            imgM500 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda500.png"))).getImage();
            imgM1000 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda1000.png"))).getImage();
            imgM1500 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda1500.png"))).getImage();
        } catch (Exception e) {
            System.out.println("Error critico al cargar las imagenes del expendedor: " + e.getMessage());
        }
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
        g.fillRect(0, 0, 530, 720);
    }
}
