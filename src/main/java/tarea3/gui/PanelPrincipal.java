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
 * Se encarga de interceptar y distribuir los eventos del mouse.
 * @author Daniel Lopez
 * @author Nicolas Silva
 * @version 1.3, 9 de junio de 2026
 */
public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;
    private Expendedor modeloExpendedor;
    private Comprador modeloComprador;

    /**
     * Constructor de la clase PanelPrincipal.
     * Inicializa el fondo, desactiva el layout por defecto, instancia el modelo logico,
     * monta las vistas de los paneles fijando sus limites y configura el escuchador
     * de eventos de mouse para la interaccion con el expendedor.
     */
    public PanelPrincipal() {
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        modeloExpendedor = new Expendedor(7);
        modeloComprador = new Comprador();
        exp = new PanelExpendedor(modeloExpendedor);
        com = new PanelComprador(modeloComprador, this);

        exp.setBounds(0, 0, 750, 720);
        com.setBounds(750, 0, 530, 720);

        this.add(com);
        this.add(exp);

        exp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 550 && x <= 660 && y >= 540 && y <= 650) {
                    modeloComprador.retirarProductoDeMaquina(modeloExpendedor);
                    com.actualizar();
                    exp.repaint();
                } else {
                    exp.manejarClick(x, y);
                }
            }
        });
    }

    /**
     * Limpia la pantalla invocando a la superclase y deja que swing maneje
     * el dibujo de forma nativa.
     * @param g El objeto graphics utilizado para pintar en el componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Permite obtener la instancia del modelo logico del expendedor
     * para comunicar los paneles.
     * @return El objeto Expendedor de la aplicacion.
     */
    public Expendedor getExpendedor() {
        return this.modeloExpendedor;
    }
}
