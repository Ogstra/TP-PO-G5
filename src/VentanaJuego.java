import javax.swing.*;
import java.awt.Dimension;

public class VentanaJuego {
    public static void main(String[] args) throws Exception {
        PanelMenu panelMenu = new PanelMenu();
        panelMenu.setPreferredSize(new Dimension(1280, 720));
        PanelPuntajes panelPuntajes = new PanelPuntajes();
        panelPuntajes.setPreferredSize(new Dimension(1280, 720));

        JFrame ventana = new JFrame("Sky Defense");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setContentPane(panelMenu);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        while (true) {
            String eleccion = panelMenu.esperarEleccion();
            if ("Salir".equals(eleccion)) break;
            if ("Puntajes".equals(eleccion)) {
                mostrarPanelPuntajes(ventana, panelMenu, panelPuntajes);
                continue;
            }
            jugarPartida(ventana, panelMenu, panelPuntajes);
        }

        ventana.dispose();
    }

    private static void jugarPartida(JFrame ventana, PanelMenu panelMenu, PanelPuntajes panelPuntajes) throws Exception {
        Jugador jugador = new Jugador("Jugador 1", 100, 3);
        Avion avion = new Avion("A1", new Posicion(640, 360), 5);
        Juego juego = new Juego(jugador, avion);
        PanelJuego panelJuego = new PanelJuego(juego);
        panelJuego.setPreferredSize(new Dimension(1280, 720));
        ControlJugador control = new ControlJugador(juego, panelJuego);

        SwingUtilities.invokeAndWait(() -> {
            ventana.setContentPane(panelJuego);
            ventana.revalidate();
            panelJuego.requestFocusInWindow();
        });

        Nivel nivel = new Nivel(1, 3, 0.008);
        juego.setNivel(nivel);
        juego.iniciar();

        boolean finalizado = false;
        while (!control.quiereSalir()) {
            if (juego.debeContinuar()) {
                control.procesarMovimientoContinuo();
                if (control.consumirDisparo()) juego.procesarLanzamientoMisil();
                juego.procesarEscuadron();
                juego.procesarCaidaMisiles();
                juego.procesarColisiones();
                if (juego.nivelCompleto()) juego.avanzarNivel();
            } else if (!finalizado) {
                juego.terminar();
                finalizado = true;
                String nombre = JOptionPane.showInputDialog(ventana, "Ingresa tu nombre:", "Game Over", JOptionPane.PLAIN_MESSAGE);
                if (nombre != null && !nombre.trim().isEmpty()) {
                    Puntajes.guardar(nombre.trim(), juego.getJugador().getPuntos());
                }
                mostrarPanelPuntajes(ventana, panelMenu, panelPuntajes);
                return;
            }
            panelJuego.repaint();
            Thread.sleep(16);
        }

        SwingUtilities.invokeAndWait(() -> {
            ventana.setContentPane(panelMenu);
            ventana.revalidate();
        });
    }

    private static void mostrarPanelPuntajes(JFrame ventana, PanelMenu panelMenu, PanelPuntajes panelPuntajes) throws Exception {
        panelPuntajes.actualizar();
        SwingUtilities.invokeAndWait(() -> {
            ventana.setContentPane(panelPuntajes);
            ventana.revalidate();
        });
        panelPuntajes.esperarVolver();
        SwingUtilities.invokeAndWait(() -> {
            ventana.setContentPane(panelMenu);
            ventana.revalidate();
        });
    }
}
