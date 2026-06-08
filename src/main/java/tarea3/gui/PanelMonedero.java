package tarea3.gui;

import tarea3.logica.Comprador;
import tarea3.logica.Moneda;

import javax.swing.*;
import java.awt.*;

public class PanelMonedero extends JPanel {
    private Comprador com;

    public PanelMonedero(Comprador com) {
        this.com = com;
        setBackground(new Color(0, 150, 150));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Recorre comprador.getMonedero() y dibuja cada moneda
        int x = 10;
        for (Moneda m : com.getMonedero()) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, 10, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString("$" + m.getValor(), x + 2, 50);
            x += 40;
        }
    }
}