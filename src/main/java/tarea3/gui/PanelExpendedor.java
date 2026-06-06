package tarea3.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import tarea3.logica.Expendedor;
import tarea3.logica.Producto;

/**
 * Clase que representa la vista y el controlador del expendedor en la interfaz grafica.
 * Se encarga de dibujar la estructura fisica de la maquina, sus compartimientos,
 * los estantes de productos y las monedas almacenadas en los depositos.
 * @author Daniel Lopez
 * @author Eduardo Riveros
 * @author Nicolas Silva
 * @version 1.1, 6 de junio de 2026
 */
public class PanelExpendedor extends JPanel {
    private Expendedor exp;

    /**
     * Constructor de la clase PanelExpendedor.
     * @param exp recibe y asigna la interfaz para conectar la vista.
     */
    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        this.setBackground(new Color(230, 230, 230));
    }

    /**
     * Procesa los clicks del mouse realizados dentro de la region del expendedor.
     * Hace el rellenado automatico de todos los depósitos de productos de la maquina.
     * @param x coordenada horizontal relativa.
     * @param y coordenada vertical relativa.
     */
    public void manejarClick(int x, int y) {
        System.out.println("Expendedor procesando click en: (" + x + ", " + y + ")");
        this.exp.rellenarDepositos();
        this.repaint();
    }

    /**
     * Realiza el renderizado y dibujo de los componentes visuales del sector del expendedor.
     * Recorre secuencialmente cada deposito del modelo para dibujar los stocks de productos.
     * @param g el objeto graphics utilizado para pintar en el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /**
         * Carcasa exterior del expendedor
         */
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 30, 420, 680);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(30, 30, 420, 680);

        g.setColor(Color.BLACK);
        g.drawString("EXPENDEDOR", 200, 25);

        /**
         * Vidrio de exhibicion (Fondo oscuro)
         */
        g.setColor(new Color(40, 45, 50));
        g.fillRect(50, 50, 380, 460);

        /**
         * Renderizado de stock: COCA-COLAS (Estante 1)
         */
        g.setColor(Color.GRAY);
        g.drawLine(50, 120, 430, 120);
        int stockCoca = exp.getDepositoCoca().getCantidad();
        for (int i = 0; i < stockCoca; i++) {
            g.setColor(Color.RED);
            g.fillRect(65 + (i * 35), 65, 25, 50);
            g.setColor(Color.WHITE);
            g.drawString("C", 73 + (i * 35), 95);
        }

        /**
         * Renderizado de stock: SPRITES (Estante 2)
         */
        g.setColor(Color.GRAY);
        g.drawLine(50, 210, 430, 210);
        int stockSprite = exp.getDepositoSprite().getCantidad();
        for (int i = 0; i < stockSprite; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(65 + (i * 35), 155, 25, 50);
            g.setColor(Color.YELLOW);
            g.drawString("S", 73 + (i * 35), 185);
        }

        /**
         * Renderizado de stock: FANTAS (Estante 3)
         */
        g.setColor(Color.GRAY);
        g.drawLine(50, 300, 430, 300);
        int stockFanta = exp.getDepositoFanta().getCantidad();
        for (int i = 0; i < stockFanta; i++) {
            g.setColor(Color.ORANGE);
            g.fillRect(65 + (i * 35), 245, 25, 50);
            g.setColor(Color.BLUE);
            g.drawString("F", 73 + (i * 35), 275);
        }

        /**
         * Renderizado de stock: SNICKERS (Estante 4)
         */
        g.setColor(Color.GRAY);
        g.drawLine(50, 390, 430, 390);
        int stockSnickers = exp.getDepositoSnickers().getCantidad();
        for (int i = 0; i < stockSnickers; i++) {
            g.setColor(new Color(100, 50, 20));
            g.fillRect(65 + (i * 50), 345, 45, 30);
            g.setColor(Color.WHITE);
            g.drawString("Snk", 75 + (i * 50), 365);
        }

        /**
         * Renderizado de stock: SUPER 8 (Estante 5)
         */
        g.setColor(Color.GRAY);
        g.drawLine(50, 480, 430, 480);
        int stockSuper8 = exp.getDepositoSuper8().getCantidad();
        for (int i = 0; i < stockSuper8; i++) {
            g.setColor(Color.BLACK);
            g.fillRect(65 + (i * 50), 435, 45, 30);
            g.setColor(Color.YELLOW);
            g.drawRect(65 + (i * 50), 435, 45, 30);
            g.drawString("S8", 78 + (i * 50), 455);
        }

        /**
         * Zona de ranura de Monedas
         */
        g.setColor(Color.BLACK);
        g.fillRect(50, 520, 380, 30);
        g.setColor(Color.GREEN);
        g.drawString("$INSERT COIN", 190, 540);

        /**
         * Deposito especial de retiro de un solo producto
         */
        g.setColor(Color.BLACK);
        g.fillRect(250, 570, 100, 110);
        g.setColor(Color.DARK_GRAY);
        g.drawString("RETIRO PRODUCTO", 245, 565);

        /**
         * Dibuja la lata/snack si hay una compra exitosa esperando
         */
        Producto prodEnEspera = exp.peekProductoRetiro();
        if (prodEnEspera != null) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(275, 600, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("Listo", 285, 630);
        }
    }
}
