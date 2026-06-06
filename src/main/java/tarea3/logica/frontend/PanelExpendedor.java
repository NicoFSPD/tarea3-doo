package org.example.frontend;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import org.example.Expendedor;
import org.example.Producto;


public class PanelExpendedor extends JPanel{
    private Expendedor exp;

    public PanelExpendedor(Expendedor exp) { //construootr
        this.exp = exp;
        this.setBackground(new Color(230, 230, 230));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 30, 420, 680);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(30, 30, 420, 680);

        g.setColor(Color.BLACK);
        g.drawString("EXPENDEDOR", 200, 25);

        g.setColor(new Color(40, 45, 50));
        g.fillRect(50, 50, 380, 460);

        //cocacolas
        g.setColor(Color.GRAY); g.drawLine(50, 120, 430, 120);
        int stockCoca = exp.getDepositoCoca().getCantidad();
        for (int i = 0; i < stockCoca; i++) {
            g.setColor(Color.RED);
            g.fillRect(65 + (i * 35), 65, 25, 50);
            g.setColor(Color.WHITE);
            g.drawString("C", 73 + (i * 35), 95);
        }
        //sprites
        g.setColor(Color.GRAY); g.drawLine(50, 210, 430, 210);
        int stockSprite = exp.getDepositoSprite().getCantidad();
        for (int i = 0; i < stockSprite; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(65 + (i * 35), 155, 25, 50);
            g.setColor(Color.YELLOW);
            g.drawString("S", 73 + (i * 35), 185);
        }

        //fantas
        g.setColor(Color.GRAY); g.drawLine(50, 300, 430, 300);
        int stockFanta = exp.getDepositoFanta().getCantidad();
        for (int i = 0; i < stockFanta; i++) {
            g.setColor(Color.ORANGE);
            g.fillRect(65 + (i * 35), 245, 25, 50);
            g.setColor(Color.BLUE);
            g.drawString("F", 73 + (i * 35), 275);
        }

        //snickers
        g.setColor(Color.GRAY); g.drawLine(50, 390, 430, 390);
        int stockSnickers = exp.getDepositoSnickers().getCantidad();
        for (int i = 0; i < stockSnickers; i++) {
            g.setColor(new Color(100, 50, 20));
            g.fillRect(65 + (i * 50), 345, 45, 30);
            g.setColor(Color.WHITE);
            g.drawString("Snk", 75 + (i * 50), 365);
        }

        //super 8
        g.setColor(Color.GRAY); g.drawLine(50, 480, 430, 480);
        int stockSuper8 = exp.getDepositoSuper8().getCantidad();
        for (int i = 0; i < stockSuper8; i++) {
            g.setColor(Color.BLACK);
            g.fillRect(65 + (i * 50), 435, 45, 30);
            g.setColor(Color.YELLOW);
            g.drawRect(65 + (i * 50), 435, 45, 30);
            g.drawString("S8", 78 + (i * 50), 455);
        }

        g.setColor(Color.BLACK);
        g.fillRect(50, 520, 380, 30);
        g.setColor(Color.GREEN);
        g.drawString("$INSERT COIN", 190, 540);

        //deposito retiro de producto
        g.setColor(Color.BLACK);
        g.fillRect(250, 570, 100, 110);
        g.setColor(Color.DARK_GRAY);
        g.drawString("RETIRO PRODUCTO", 245, 565);

        Producto prodEnEspera = exp.peekProductoRetiro();
        if (prodEnEspera != null) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(275, 600, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("Listo", 285, 630);
        }
    }

    //rellenar depositos
    public void manejarClick() {
        this.exp.rellenarDepositos();
        this.repaint();
    }
}