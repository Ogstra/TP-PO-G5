import javax.swing.*;

public class VentanaJuego {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Jugador 1", 100, 3);
        Avion avion = new Avion("A1", new Posicion(400, 300), 5);
        Juego juego = new Juego(jugador, avion);

        PanelJuego panel = new PanelJuego(juego);
        ControlJugador control = new ControlJugador(juego, panel);

        JFrame ventana = new JFrame("TPO Paradigmas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.add(panel);
        ventana.setVisible(true);
        panel.requestFocusInWindow();

        Nivel nivel = new Nivel(1, 3, 0.02);
        juego.setNivel(nivel);
        juego.iniciar();

        // Game loop
        while (juego.debeContinuar() && !control.quiereSalir()) {
            control.procesarMovimientoContinuo();
            juego.procesarEscuadron();
            juego.procesarCaidaMisiles();
            if (juego.nivelCompleto()) {
                juego.avanzarNivel();
            }
            panel.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }

        juego.terminar();
        ventana.dispose();
    }
}
