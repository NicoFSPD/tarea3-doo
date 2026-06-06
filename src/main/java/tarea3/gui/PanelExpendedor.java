package tarea3.gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;
import tarea3.logica.Expendedor;
import tarea3.logica.Producto;

/**
 * Clase que representa la vista y el controlador del expendedor en la interfaz grafica.
 * Se encarga de dibujar la estructura fisica de la maquina, sus compartimientos,
 * los estantes de productos y las imagenes correspondientes a cada elemento en stock.
 * @author Daniel Lopez
 * @author Eduardo Riveros
 * @author Nicolas Silva
 * @version 1.4, 6 de junio de 2026
 */
public class PanelExpendedor extends JPanel {
    private Expendedor exp;

    // Objetos Image para almacenar las texturas cargadas
    private Image imgCoca;
    private Image imgSprite;
    private Image imgFanta;
    private Image imgSnickers;
    private Image imgSuper8;

    /**
     * Constructor de la clase PanelExpendedor.
     * Enlaza el modelo logico, configura el fondo y precarga las imagenes desde los recursos.
     * @param exp recibe y asigna la interfaz para conectar la vista.
     */
    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        this.setBackground(new Color(230, 230, 230));
        cargarImagenes();
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
        } catch (Exception e) {
            System.out.println("Error critico al cargar las imagenes del expendedor: " + e.getMessage());
        }
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
     * Recorre secuencialmente cada deposito del modelo para renderizar las imagenes en stock.
     * @param g el objeto graphics utilizado para pintar en el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /**
         * Carcasa exterior del expendedor (Centrada y extendida)
         */
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 30, 650, 640);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(30, 30, 650, 640);

        g.setColor(Color.BLACK);
        g.drawString("EXPENDEDOR", 315, 25);

        /**
         * Vidrio de exhibicion (Fondo oscuro centrado simetricamente)
         */
        g.setColor(new Color(40, 45, 50));
        g.fillRect(50, 50, 610, 440);

        /**
         * Renderizado de stock: COCA-COLAS (Estante 1)
         */
        g.setColor(Color.GRAY);
        g.drawLine(50, 120, 660, 120);
        int stockCoca = exp.getDepositoCoca().getCantidad();
        for (int i = 0; i < stockCoca; i++) {
            g.drawImage(imgCoca, 150 + (i * 60), 60, 30, 55, this);
        }

        /**
         * Renderizado de stock: SPRITES (Estante 2)
         */
        g.drawLine(50, 210, 660, 210);
        int stockSprite = exp.getDepositoSprite().getCantidad();
        for (int i = 0; i < stockSprite; i++) {
            g.drawImage(imgSprite, 150 + (i * 60), 150, 30, 55, this);

        }

        /**
         * Renderizado de stock: FANTAS (Estante 3)
         */
        g.drawLine(50, 300, 660, 300);
        int stockFanta = exp.getDepositoFanta().getCantidad();
        for (int i = 0; i < stockFanta; i++) {
            g.drawImage(imgFanta, 150 + (i * 60), 240, 30, 55, this);
        }

        /**
         * Renderizado de stock: SNICKERS (Estante 4)
         */
        g.drawLine(50, 390, 660, 390);
        int stockSnickers = exp.getDepositoSnickers().getCantidad();
        for (int i = 0; i < stockSnickers; i++) {
            g.drawImage(imgSnickers, 100 + (i * 75), 345, 60, 30, this);
        }

        /**
         * Renderizado de stock: SUPER 8 (Estante 5)
         */
        g.drawLine(50, 480, 660, 480);
        int stockSuper8 = exp.getDepositoSuper8().getCantidad();
        for (int i = 0; i < stockSuper8; i++) {
            g.drawImage(imgSuper8, 90 + (i * 75), 435, 70, 40, this);
        }

        /**
         * Deposito especial de retiro de un solo producto (Centrado matematico)
         */
        g.setColor(Color.BLACK);
        g.fillRect(300, 540, 110, 110);
        g.setColor(Color.DARK_GRAY);
        g.drawString("RETIRO PRODUCTO", 305, 535);

        /**
         * Dibuja la imagen real del producto si hay una compra exitosa esperando
         */
        Producto prodEnEspera = exp.peekProductoRetiro();
        if (prodEnEspera != null) {
            String tipo = prodEnEspera.getClass().getSimpleName();
            Image imgA_Dibujar = null;

            if (tipo.equals("CocaCola")) imgA_Dibujar = imgCoca;
            else if (tipo.equals("Sprite")) imgA_Dibujar = imgSprite;
            else if (tipo.equals("Fanta")) imgA_Dibujar = imgFanta;
            else if (tipo.equals("Snickers")) imgA_Dibujar = imgSnickers;
            else if (tipo.equals("Super8")) imgA_Dibujar = imgSuper8;

            if (imgA_Dibujar != null) {

                if (tipo.equals("Snickers") || tipo.equals("Super8")) {
                    g.drawImage(imgA_Dibujar, 333, 580, 45, 30, this);
                } else {
                    g.drawImage(imgA_Dibujar, 343, 570, 25, 50, this);
                }
            }
        }
    }
}
