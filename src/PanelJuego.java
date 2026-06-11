import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel {
    private Juego juego;

    public PanelJuego(Juego juego) {
        this.juego = juego;
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Avion
        Avion avion = juego.getAvion();
        if (avion.estaActivo()) {
            int ax = (int) avion.getPosicion().getX();
            int ay = (int) avion.getPosicion().getY();

            // Alas
            g.setColor(new Color(0, 180, 200));
            g.fillPolygon(
                new int[]{ax - 5, ax - 24, ax - 10, ax},
                new int[]{ay, ay + 8, ay + 10, ay + 2},
                4
            );
            g.fillPolygon(
                new int[]{ax + 5, ax + 24, ax + 10, ax},
                new int[]{ay, ay + 8, ay + 10, ay + 2},
                4
            );

            // Cuerpo
            g.setColor(Color.CYAN);
            g.fillPolygon(
                new int[]{ax, ax - 8, ax - 5, ax + 5, ax + 8},
                new int[]{ay - 22, ay + 10, ay + 6, ay + 6, ay + 10},
                5
            );

            // Motor
            g.setColor(Color.ORANGE);
            g.fillRect(ax - 3, ay + 6, 6, 6);
        }

        // Drones
        g.setColor(Color.GREEN);
        for (Drone drone : juego.getDronesActivos()) {
            int dx = (int) drone.getPosicion().getX();
            int dy = (int) drone.getPosicion().getY();
            g.fillPolygon(
                new int[]{dx, dx - 12, dx - 8, dx + 8, dx + 12},
                new int[]{dy + 8, dy - 4, dy + 4, dy + 4, dy - 4},
                5
            );
        }

        // Misiles
        g.setColor(Color.RED);
        for (Misil misil : juego.getMisiles()) {
            if (!misil.estaDetonado()) {
                g.fillOval((int) misil.getPosicion().getX() - 4, (int) misil.getPosicion().getY() - 4, 8, 8);
            }
        }

        // HUD
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));
        g.drawString("Puntos: " + juego.getJugador().getPuntos(), 10, 20);
        g.drawString("Vida: " + juego.getJugador().getVida(), 10, 40);
        g.drawString("Vidas: " + juego.getJugador().getVidasRestantes(), 10, 60);

        if (!juego.isEnCurso()) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Monospaced", Font.BOLD, 32));
            g.drawString("GAME OVER", getWidth() / 2 - 90, getHeight() / 2);
        }
    }
}
