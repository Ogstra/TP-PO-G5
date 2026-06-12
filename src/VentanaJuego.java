import javax.swing.*;
import java.awt.Dimension;

public class VentanaJuego {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Jugador 1", 100, 3);
        Avion avion = new Avion("A1", new Posicion(640, 360), 5);
        Juego juego = new Juego(jugador, avion);

        PanelJuego panel = new PanelJuego(juego);
        panel.setPreferredSize(new Dimension(1280, 720)); // area de juego 16:9
        ControlJugador control = new ControlJugador(juego, panel);

        JFrame ventana = new JFrame("TPO Paradigmas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.add(panel);
        ventana.pack(); // ajusta la ventana al area 1280x720 exacta
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        panel.requestFocusInWindow();

        Nivel nivel = new Nivel(1, 3, 0.008);
        juego.setNivel(nivel);
        juego.iniciar();

        boolean finalizado = false;

        // Loop principal: corre hasta que el jugador pulse P (salir)
        while (!control.quiereSalir()) {
            if (juego.debeContinuar()) {
                control.procesarMovimientoContinuo();
                juego.procesarEscuadron();
                juego.procesarCaidaMisiles();
                juego.procesarColisiones();
                if (juego.nivelCompleto()) {
                    juego.avanzarNivel();
                }
            } else if (!finalizado) {
                // Game over: finaliza una vez, deja la ventana abierta con el cartel
                juego.terminar();
                finalizado = true;
            }
            panel.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }

        ventana.dispose();
    }
}
