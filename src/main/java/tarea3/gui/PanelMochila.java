package tarea3.gui;

import tarea3.logica.Comprador;
import tarea3.logica.Producto;

import javax.swing.*;
import java.awt.*;

public class PanelMochila extends JPanel {
    private Comprador com;

    public PanelMochila(Comprador com) {
        this.com = com;
        setBackground(new Color(0, 130, 130));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Recorre comprador.getMochila() y dibuja cada producto
        int x = 10;
        for (Producto p : com.getMochila()) {
            g.setColor(Color.WHITE);
            g.fillRect(10, 10, 50, 70);
            g.setColor(Color.BLACK);
            g.drawString(p.getClass().getSimpleName(), x, 95);
            x += 60;
        }
    }
}