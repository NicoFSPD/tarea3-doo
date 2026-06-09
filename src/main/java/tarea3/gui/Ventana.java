package tarea3.gui;

import javax.swing.*;

/**
 * Clase Ventana encargada de ser el espacio en donde se maneja el PanelPrincipal,
 * el cual es el encargado de la funcionalidad de toda la interfaz grafica.
 * @author Eduardo Riveros
 * @author Daniel Lopez
 * @version 1.1, 5 de junio de 2026
 */

public class Ventana extends JFrame {
    /**
     * Metodo constructor de la clase Ventana creada apartir
     * de la superclase JFrame y de otras configuraciones base
     * como darle nombre a la ventana, la opcion para que se pueda
     * cerrar el programa directamente, la integracion de la clase
     * PanelPrincipal para el funcionamiento del programa,
     * la resolucion, una funcion que hace que se centre en la pantalla,
     * y tambien para que sea visible.
     */
    public Ventana(){
        super();
        this.setTitle("Maquina expendedora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        this.add(panelPrincipal);
        this.setSize(1290, 755);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}