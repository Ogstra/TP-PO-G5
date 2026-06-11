import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Jugador jugador;
    private Avion avion;
    private List<Misil> misiles;
    private boolean enCurso;
    private int proximaVidaExtra = 1000;

    public Juego(Jugador jugador, Avion avion) {
        this.jugador = jugador;
        this.avion = avion;
        this.misiles = new ArrayList<>();
        this.enCurso = false;
    }

    public void iniciar() {
        this.enCurso = true;
    }

    public void procesarMovimientoAvion(double dx, double dy) {
        avion.mover(dx, dy);
    }

    public void procesarLanzamientoMisil() {
        Misil nuevoMisil = avion.generarMisil();
        if (nuevoMisil != null) {
            misiles.add(nuevoMisil);
            avion.lanzarMisil(nuevoMisil);
        }
    }

    public void procesarDetonaciones() {
        for (int i = misiles.size() - 1; i >= 0; i--) {
            Misil misil = misiles.get(i);

            if (!misil.estaDetonado()) {
                misil.lanzar();

                Explosion explosion = misil.detonar();

                if(explosion != null) {
                    aplicarDanioExplosion(explosion);
                    actualizarPuntosYVida(explosion);

                    misiles.remove(i);
                }
            } else {
                misiles.remove(i);
            }
        }

        if(!debeContinuar()) {
            terminar();
        }
    }

    public void aplicarDanioExplosion(Explosion explosion) {
        double distancia = avion.getPosicion().distanciaA(explosion.getEpicentro());
        if (distancia <= explosion.getRadioEfecto()) {
            double danio = explosion.getPotencia() * (1 - (distancia / explosion.getRadioEfecto()));
            avion.recibirDanio(danio);
            jugador.recibirDanio(danio);
        }
    }

    public void actualizarPuntosYVida(Explosion explosion) {
        if (avion.estaActivo()) {
            jugador.sumarPuntos(100);
        } else {
            jugador.restarPuntos(50);
            jugador.perderVida();
        }

        if (jugador.getPuntos() >= proximaVidaExtra) {
            jugador.sumarPuntos(100); // Bonus por alcanzar puntos
            proximaVidaExtra += 1000; // Incrementar el umbral para la próxima vida extra
        }
    }

    public boolean debeContinuar() {
        return jugador.estaVivo() && avion.estaActivo();
    }

    public void terminar() {
        this.enCurso = false;
    }

    public Jugador getJugador() { return jugador; }
    public Avion getAvion() { return avion; }
    public List<Misil> getMisiles() { return misiles; }
    public boolean isEnCurso() { return enCurso; }
}
