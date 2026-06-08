package tarea3.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import tarea3.logica.Comprador;
import tarea3.logica.Expendedor;

/**
 * Clase contenedora que unifica la interfaz grafica de la aplicacion.
 * Actua como el panel central de la ventana, integrando el comprador y el expendedor dentro de si.
 * Se encarga de interceptar y distribuir los eventos del mouse.
 * @author Daniel Lopez
 * @version 1.1, 2 de junio de 2026
 */

public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;
    private Expendedor modeloExpendedor;
    private Comprador modeloComprador;

    /**
     * Constructor de la clase PanelPrincipal.
     * Inicializa el fondo, desactiva el layout por defecto, instancia el modelo logico,
     * monta las vistas de los paneles fijando sus limites y configura el escuchador de eventos de mouse.
     */
    public PanelPrincipal() {
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        /**
         * Iniciacion de modelo logico y de las vistas
         */
        modeloExpendedor = new Expendedor(7);
        modeloComprador = new Comprador();
        exp = new PanelExpendedor(modeloExpendedor);
        com = new PanelComprador(modeloComprador, this);

        /**
         * Dimensiones fisicas de cada panel en pantalla
         */
        exp.setBounds(0, 0, 750, 720);
        com.setBounds(750, 0, 530, 720);

        /**
         * Agregar componentes al contenedor para que Swing los reconosca
         */
        this.add(com);
        this.add(exp);

        /**
         * Captura los eventos del mouse
         */
        this.addMouseListener(new MouseAdapter() {
            /**
             * Evalua la posicion del click del mouse para redirigir el evento al panel correcto.
             * @param e el evento del mouse que contiene las coordenadas del click.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() < 600) {
                    exp.manejarClick(e.getX(), e.getY());
                } else {
                    com.manejarClick(e.getX() - 600, e.getY());
                }
                repaint();
            }
        });
    }

    /**
     * Limpia la pantalla invocando a la superclase y deja que Swing maneje el dibujo de forma nativa.
     * @param g el objeto graphics utilizado para pintar en el componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    /**
     * Permite obtener la instancia del expendedor para comunicar los paneles.
     * @return el objeto Expendedor de la aplicacion.
     */
    public Expendedor getExpendedor() {
        return this.modeloExpendedor;
    }
}