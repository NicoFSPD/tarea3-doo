package tarea3.gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;
import tarea3.logica.Expendedor;
import tarea3.logica.Moneda;
import tarea3.logica.Moneda100;
import tarea3.logica.Moneda500;
import tarea3.logica.Moneda1000;
import tarea3.logica.Producto;

/**
 * Clase contenedora que representa la vista del expendedor en la interfaz grafica.
 * Se encarga de dibujar la estructura fisica de la maquina, sus compartimientos,
 * los estantes de productos y las imagenes correspondientes a cada elemento en stock o almacenado.
 * @author Daniel Lopez
 * @author Eduardo Riveros
 * @author Nicolas Silva
 * @version 1.6, 9 de junio de 2026
 */
public class PanelExpendedor extends JPanel {
    private Expendedor exp;

    private Image imgCoca;
    private Image imgSprite;
    private Image imgFanta;
    private Image imgSnickers;
    private Image imgSuper8;

    private Image imgM100;
    private Image imgM500;
    private Image imgM1000;
    private Image imgM1500;

    /**
     * Constructor de la clase PanelExpendedor.
     * Enlaza el modelo logico, configura el color de fondo, activa los tooltips
     * y precarga las imagenes necesarias desde los recursos del proyecto.
     * @param exp El objeto Expendedor que sirve como modelo logico para esta vista.
     */
    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        this.setBackground(new Color(230, 230, 230));
        this.setToolTipText("");
        cargarImagenes();
    }

    /**
     * Carga de forma segura los archivos de imagen desde la carpeta de recursos.
     * Asigna las texturas de productos y monedas a sus respectivas variables,
     * manejando excepciones para reportar fallos en la consola.
     */
    private void cargarImagenes() {
        try {
            imgCoca = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cocacola.png"))).getImage();
            imgSprite = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/sprite.png"))).getImage();
            imgFanta = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/fanta.png"))).getImage();
            imgSnickers = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/snickers.png"))).getImage();
            imgSuper8 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/super8.png"))).getImage();

            imgM100 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda100.png"))).getImage();
            imgM500 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda500.png"))).getImage();
            imgM1000 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda1000.png"))).getImage();
            imgM1500 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/moneda1500.png"))).getImage();
        } catch (Exception e) {
            System.out.println("Error critico al cargar las imagenes del expendedor: " + e.getMessage());
        }
    }

    /**
     * Procesa los eventos de click realizados dentro de la region del expendedor.
     * Invoca el rellenado automatico de todos los depositos de la maquina y fuerza
     * un repintado de la interfaz para actualizar el stock visual.
     * @param x La coordenada horizontal relativa al panel.
     * @param y La coordenada vertical relativa al panel.
     */
    public void manejarClick(int x, int y) {
        System.out.println("Expendedor procesando click en: (" + x + ", " + y + ")");
        this.exp.rellenarDepositos();
        this.repaint();
    }

    /**
     * Calcula la posicion del puntero dentro de la cuadricula de la maquina.
     * Retorna la informacion exacta del producto o moneda apuntada segun las
     * coordenadas especificas de los estantes y depositos.
     * @param event El evento del mouse que contiene las coordenadas actuales.
     * @return Una cadena de texto con los detalles del objeto, o el comportamiento por defecto.
     */
    @Override
    public String getToolTipText(java.awt.event.MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        if ((y >= 60) && (y <= 115)) {
            int stock = exp.getDepositoCoca().getCantidad();
            for (int i = 0; i < stock; i++) {
                int posX = (150 + (i * 60));
                if ((x >= posX) && (x <= (posX + 30))) {
                    Producto p = exp.getDepositoCoca().getElementoPorIndice(i);
                    return "Coca-Cola (Serie: " + p.getSerie() + ")";
                }
            }
        }

        if ((y >= 150) && (y <= 205)) {
            int stock = exp.getDepositoSprite().getCantidad();
            for (int i = 0; i < stock; i++) {
                int posX = (150 + (i * 60));
                if ((x >= posX) && (x <= posX + 30)) {
                    Producto p = exp.getDepositoSprite().getElementoPorIndice(i);
                    return "Sprite (Serie: " + p.getSerie() + ")";
                }
            }
        }

        if ((y >= 240) && (y <= 295)) {
            int stock = exp.getDepositoFanta().getCantidad();
            for (int i = 0; i < stock; i++) {
                int posX = (150 + (i * 60));
                if ((x >= posX) && (x <= posX + 30)) {
                    Producto p = exp.getDepositoFanta().getElementoPorIndice(i);
                    return "Fanta (Serie: " + p.getSerie() + ")";
                }
            }
        }

        if ((y >= 345) && (y <= 375)) {
            int stock = exp.getDepositoSnickers().getCantidad();
            for (int i = 0; i < stock; i++) {
                int posX = (100 + (i * 75));
                if ((x >= posX) && (x <= posX + 60)) {
                    Producto p = exp.getDepositoSnickers().getElementoPorIndice(i);
                    return "Snickers (Serie: " + p.getSerie() + ")";
                }
            }
        }

        if ((y >= 435) && (y <= 475)) {
            int stock = exp.getDepositoSuper8().getCantidad();
            for (int i = 0; i < stock; i++) {
                int posX = 90 + (i * 75);
                if ((x >= posX) && (x <= (posX + 70))) {
                    Producto p = exp.getDepositoSuper8().getElementoPorIndice(i);
                    return "Super 8 (Serie: " + p.getSerie() + ")";
                }
            }
        }

        if ((x >= 550) && (x <= 660) && (y >= 540) && (y <= 650)) {
            Producto prodEnEspera = exp.peekProductoRetiro();
            if (prodEnEspera != null) {
                return prodEnEspera.getClass().getSimpleName() + " en Retiro (Serie: " + prodEnEspera.getSerie() + ")";
            }
        }

        if ((x >= 50) && (x <= 520) && (y >= 540) && (y <= 650)) {
            int cantMonedas = exp.getCantidadMonedasAlmacenadas();
            for (int i = 0; i < cantMonedas; i++) {
                int fila = i / 15;
                int col = i % 15;
                int posX = 65 + (col * 30);
                int posY = 550 + (fila * 30);

                if ((x >= posX) && (x <= (posX + 25)) && (y >= posY) && (y <= (posY + 25))) {
                    Moneda m = exp.getMonedaAlmacenadaPorIndice(i);
                    if (m != null) {
                        return "Moneda de $" + m.getValor() + " (Serie: " + m.hashCode() + ")";
                    }
                }
            }
        }

        return super.getToolTipText(event);
    }

    /**
     * Realiza el renderizado y dibujo de los componentes visuales del expendedor.
     * Construye la carcasa, los estantes, los productos en stock, el deposito de retiro,
     * el producto en espera y la caja fuerte con las monedas almacenadas.
     * @param g El objeto graphics utilizado para pintar en el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 30, 650, 640);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(30, 30, 650, 640);

        g.setColor(Color.BLACK);
        g.drawString("EXPENDEDOR", 315, 25);

        g.setColor(new Color(40, 45, 50));
        g.fillRect(50, 50, 610, 440);

        g.setColor(Color.GRAY);
        g.drawLine(50, 120, 660, 120);
        int stockCoca = exp.getDepositoCoca().getCantidad();
        for (int i = 0; i < stockCoca; i++) {
            g.drawImage(imgCoca, 150 + (i * 60), 60, 30, 55, this);
        }

        g.drawLine(50, 210, 660, 210);
        int stockSprite = exp.getDepositoSprite().getCantidad();
        for (int i = 0; i < stockSprite; i++) {
            g.drawImage(imgSprite, 150 + (i * 60), 150, 30, 55, this);
        }

        g.drawLine(50, 300, 660, 300);
        int stockFanta = exp.getDepositoFanta().getCantidad();
        for (int i = 0; i < stockFanta; i++) {
            g.drawImage(imgFanta, 150 + (i * 60), 240, 30, 55, this);
        }

        g.drawLine(50, 390, 660, 390);
        int stockSnickers = exp.getDepositoSnickers().getCantidad();
        for (int i = 0; i < stockSnickers; i++) {
            g.drawImage(imgSnickers, 100 + (i * 75), 345, 60, 30, this);
        }

        g.drawLine(50, 480, 660, 480);
        int stockSuper8 = exp.getDepositoSuper8().getCantidad();
        for (int i = 0; i < stockSuper8; i++) {
            g.drawImage(imgSuper8, 90 + (i * 75), 435, 70, 40, this);
        }

        g.setColor(Color.BLACK);
        g.fillRect(550, 540, 110, 110);
        g.setColor(Color.DARK_GRAY);
        g.drawString("RETIRO PRODUCTO", 550, 535);

        g.setColor(new Color(60, 60, 60));
        g.fillRect(50, 540, 470, 110);
        g.setColor(Color.DARK_GRAY);
        g.drawString("MONEDAS INTERNAS", 50, 535);

        int cantMonedas = exp.getCantidadMonedasAlmacenadas();
        for (int i = 0; i < cantMonedas; i++) {
            int fila = i / 15;
            int col = i % 15;
            int posX = 65 + (col * 30);
            int posY = 550 + (fila * 30);

            if (posY < 640) {
                try {
                    Moneda m = exp.getMonedaAlmacenadaPorIndice(i);
                    Image imgMoneda = null;

                    if (m instanceof Moneda100) imgMoneda = imgM100;
                    else if (m instanceof Moneda500) imgMoneda = imgM500;
                    else if (m instanceof Moneda1000) imgMoneda = imgM1000;
                    else if (m != null && m.getClass().getSimpleName().equals("Moneda1500")) imgMoneda = imgM1500;

                    if (imgMoneda != null) {
                        g.drawImage(imgMoneda, posX, posY, 25, 25, this);
                    }
                } catch (Exception e) {
                }
            }
        }

        Producto pRetiro = exp.peekProductoRetiro();
        if (pRetiro != null) {
            String tipo = pRetiro.getClass().getSimpleName();
            Image imgA_Dibujar = null;

            int ancho = 30;
            int alto = 55;
            int x = 590;
            int y = 567;

            if (tipo.equals("CocaCola")) {
                imgA_Dibujar = imgCoca;
            } else if (tipo.equals("Sprite")) {
                imgA_Dibujar = imgSprite;
            } else if (tipo.equals("Fanta")) {
                imgA_Dibujar = imgFanta;
            } else if (tipo.equals("Snickers")) {
                imgA_Dibujar = imgSnickers;
                ancho = 60;
                alto = 30;
                x = 575;
                y = 580;
            } else if (tipo.equals("Super8")) {
                imgA_Dibujar = imgSuper8;
                ancho = 70;
                alto = 40;
                x = 570;
                y = 575;
            }

            if (imgA_Dibujar != null) {
                g.drawImage(imgA_Dibujar, x, y, ancho, alto, this);
            }
        }
    }
}