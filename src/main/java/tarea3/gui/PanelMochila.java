package tarea3.gui;

import tarea3.logica.Comprador;
import tarea3.logica.Producto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Clase contenedora que representa visualmente la mochila del comprador.
 * Dibuja los productos adquiridos en una matriz imaginaria de 3 filas por 8 columnas.
 * Proporciona informacion detallada de cada producto mediante ToolTips interactivos.
 * @author Nicolas Silva
 * @author Daniel Lopez
 * @version 1.0, 9 de junio de 2026
 */
public class PanelMochila extends JPanel {
    private Comprador com;
    private Image imgCoca;
    private Image imgSprite;
    private Image imgFanta;
    private Image imgSnickers;
    private Image imgSuper8;

    /**
     * Constructor de la clase PanelMochila.
     * Inicializa el fondo del panel, activa la visualizacion de tootTips y
     * carga las imagenes correspondientes a cada tipo de producto.
     * @param com El objeto Comprador que contiene la lista de productos adquiridos.
     */
    public PanelMochila(Comprador com) {
        this.com = com;
        setBackground(Color.WHITE);
        setToolTipText("");
        cargarImagenes();
    }

    /**
     * Carga las imagenes de los productos desde los recursos del proyecto.
     * Asigna cada imagen a su variable correspondiente ignorando excepciones en caso de fallo.
     */
    private void cargarImagenes() {
        try {
            imgCoca = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cocacola.png"))).getImage();
            imgSprite = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/sprite.png"))).getImage();
            imgFanta = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/fanta.png"))).getImage();
            imgSnickers = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/snickers.png"))).getImage();
            imgSuper8 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/super8.png"))).getImage();
        } catch (Exception e) {
        }
    }

    /**
     * Retorna el tipo de producto y su numero de serie especifico.
     * @param event El evento del mouse que contiene las coordenadas actuales.
     * @return Una cadena de texto con el formato "NombreProducto (Serie: X)", o el comportamiento por defecto.
     */
    @Override
    public String getToolTipText(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        if (x >= 10 && y >= 10) {
            int col = (x - 10) / 60;
            int row = (y - 10) / 70;
            int modX = (x - 10) % 60;
            int modY = (y - 10) % 70;

            if (col < 8 && row < 3 && modX <= 60 && modY <= 70) {
                int index = (row * 8) + col;

                if (index >= 0 && index < com.getMochila().size() && index < 24) {
                    Producto p = com.getMochila().get(index);
                    return p.getClass().getSimpleName() + " (Serie: " + p.getSerie() + ")";
                }
            }
        }
        return super.getToolTipText(event);
    }

    /**
     * Itera sobre la mochila y ubica las imagenes de los productos
     * calculando filas y columnas.
     * @param g El objeto graphics utilizado para pintar en el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = com.getMochila().size();

        for (int i = 0; i < size; i++) {
            if (i >= 24) {
                break;
            }

            Producto p = com.getMochila().get(i);
            String tipo = p.getClass().getSimpleName();
            Image imgA_Dibujar = null;

            int ancho = 30;
            int alto = 55;
            int offsetX = 0;
            int offsetY = 10;

            if (tipo.equals("CocaCola")) {
                imgA_Dibujar = imgCoca;
                offsetX = 20;
            } else if (tipo.equals("Sprite")) {
                imgA_Dibujar = imgSprite;
                offsetX = 20;
            } else if (tipo.equals("Fanta")) {
                imgA_Dibujar = imgFanta;
                offsetX = 20;
            } else if (tipo.equals("Snickers")) {
                imgA_Dibujar = imgSnickers;
                ancho = 60;
                alto = 30;
                offsetX = 5;
                offsetY = 25;
            } else if (tipo.equals("Super8")) {
                imgA_Dibujar = imgSuper8;
                ancho = 70;
                alto = 40;
                offsetY = 25;
            }

            if (imgA_Dibujar != null) {
                int col = i % 8;
                int row = i / 8;

                int x = 10 + (col * 60) + offsetX;
                int y = (row * 70) + offsetY;

                g.drawImage(imgA_Dibujar, x, y, ancho, alto, this);
            }
        }
    }
}
