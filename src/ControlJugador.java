import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

// Captura el teclado: WASD mueve el avion, M dispara, P sale.
public class ControlJugador {
    private Juego juego;
    private Set<Integer> teclasPresionadas = new HashSet<>();
    private volatile boolean disparoPendiente = false;

    public ControlJugador(Juego juego, JComponent componente) {
        this.juego = juego;

        componente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                teclasPresionadas.add(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    disparoPendiente = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                teclasPresionadas.remove(e.getKeyCode());
            }
        });
    }

    public void procesarMovimientoContinuo() {
        double vel = juego.getAvion().getVelocidad();
        if (teclasPresionadas.contains(KeyEvent.VK_W)) juego.procesarMovimientoAvion(0, -vel);
        if (teclasPresionadas.contains(KeyEvent.VK_S)) juego.procesarMovimientoAvion(0, vel);
        if (teclasPresionadas.contains(KeyEvent.VK_A)) juego.procesarMovimientoAvion(-vel, 0);
        if (teclasPresionadas.contains(KeyEvent.VK_D)) juego.procesarMovimientoAvion(vel, 0);
    }

    // Consume el disparo pendiente. Retorna true una sola vez por presion de M
    public boolean consumirDisparo() {
        if (disparoPendiente) {
            disparoPendiente = false;
            return true;
        }
        return false;
    }

    public boolean quiereSalir() {
        return teclasPresionadas.contains(KeyEvent.VK_P);
    }
}
