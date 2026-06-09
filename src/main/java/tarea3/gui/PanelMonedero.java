package tarea3.gui;

import tarea3.logica.Comprador;
import tarea3.logica.Moneda;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Clase contenedora que representa visualmente el monedero del comprador.
 * Dibuja las monedas en una matriz imaginaria de 4 filas por 12 columnas.
 * Proporciona informacion detallada de cada moneda mediante ToolTips interactivos.
 * @author Nicolas Silva
 * @author Daniel Lopez
 * @version 1.1, 9 de junio de 2026
 */
public class PanelMonedero extends JPanel {
    private Comprador com;
    private Image imgM100;
    private Image imgM500;
    private Image imgM1000;
    private Image imgM1500;

    /**
     * Constructor de la clase PanelMonedero.
     * Inicializa el fondo del panel, activa la visualizacion de tooltips y
     * carga las imagenes correspondientes a cada denominacion de moneda.
     * @param com El objeto Comprador que contiene la lista de monedas a visualizar.
     */
    public PanelMonedero(Comprador com) {
        this.com = com;
        setBackground(new Color(0, 150, 150));
        setToolTipText("");
        cargarImagenes();
    }

    /**
     * Carga las imagenes de las monedas desde los recursos del proyecto.
     * Asigna cada imagen a su variable correspondiente.
     */
    private void cargarImagenes() {
        try {
            imgM100 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda100.png"))).getImage();
            imgM500 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda500.png"))).getImage();
            imgM1000 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda1000.png"))).getImage();
            imgM1500 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda1500.png"))).getImage();
        } catch (Exception e) {
        }
    }

    /**
     * Retorna el valor y el numero de serie especifico de la moneda apuntada.
     * @param event El evento del mouse que contiene las coordenadas actuales.
     * @return Una cadena de texto con el formato "Moneda de $X (Serie: Y)", o el comportamiento por defecto.
     */
    @Override
    public String getToolTipText(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        if (x >= 10 && y >= 10) {
            int col = (x - 10) / 40;
            int row = (y - 10) / 40;
            int modX = (x - 10) % 40;
            int modY = (y - 10) % 40;

            if (col < 12 && row < 4 && modX <= 30 && modY <= 30) {
                int index = (row * 12) + col;

                if (index >= 0 && index < com.getMonedero().size() && index < 48) {
                    Moneda m = com.getMonedero().get(index);
                    return "Moneda de $" + m.getValor() + " (Serie: " + m.hashCode() + ")";
                }
            }
        }
        return super.getToolTipText(event);
    }

    /**
     * Itera sobre el monedero y posiciona las imagenes de las monedas calculando
     * filas y columnas.
     * @param g El objeto graphics utilizado para pintar en el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = com.getMonedero().size();

        for (int i = 0; i < size; i++) {
            if (i >= 48) {
                break;
            }

            Moneda m = com.getMonedero().get(i);
            Image imgA_Dibujar = null;
            int valor = m.getValor();

            if (valor == 100) {
                imgA_Dibujar = imgM100;
            } else if (valor == 500) {
                imgA_Dibujar = imgM500;
            } else if (valor == 1000) {
                imgA_Dibujar = imgM1000;
            } else if (valor == 1500) {
                imgA_Dibujar = imgM1500;
            }

            if (imgA_Dibujar != null) {
                int col = i % 12;
                int row = i / 12;

                int x = 10 + (col * 40);
                int y = 10 + (row * 40);

                g.drawImage(imgA_Dibujar, x, y, 30, 30, this);
            }
        }
    }
}
