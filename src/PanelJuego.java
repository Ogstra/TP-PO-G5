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
            g.setColor(Color.CYAN);
            g.fillPolygon(
                new int[]{(int) avion.getPosicion().getX(), (int) avion.getPosicion().getX() - 15, (int) avion.getPosicion().getX() + 15},
                new int[]{(int) avion.getPosicion().getY() - 20, (int) avion.getPosicion().getY() + 10, (int) avion.getPosicion().getY() + 10},
                3
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
