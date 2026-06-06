package tarea3.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import tarea3.logica.Expendedor;

/**
 * Clase que representa la vista y el controlador del expendedor en la interfaz grafica.
 * Se encarga de dibujar la estructura fisica de la maquina, sus compartimientos,
 * los estantes de productos y las monedas almacenadas en los depositos.
 * @author Daniel Lopez
 * @version 1.0, 2 de junio de 2026
 */
public class PanelExpendedor extends JPanel {
    private Expendedor exp;

    /**
     * Constructor de la clase PanelExpendedor.
     * @param exp recibe y asigna el modelo logico del expendedor para conectar la vista.
     */
    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
    }

    /**Procesa y evalua los clicks del mouse realizados dentro de la region del expendedor.
     * Permite gatillar el rellenado de los depositos si estos se encuentran vacios.
     * @param x coordenada horizontal
     * @param y coordenada vertical
     */
    public void manejarClick(int x, int y) {
    }

    /**Realiza el renderizado y dibujo de los componentes visuales del sector del expendedor.
     * Dibuja carcasa de la maquina gris y el deposito oscuro de un solo producto.
     * @param g el objeto graphics utilizado para pintar en el componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(50, 50, 700, 600);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(550, 500, 100, 100);
    }
}
