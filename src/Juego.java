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

                if (explosion != null) {
                    aplicarDanioExplosion(explosion);
                    actualizarPuntosYVida(explosion);

                    misiles.remove(i);
                }
            } else {
                misiles.remove(i);
            }
        }

        if (!debeContinuar()) {
            terminar();
        }
    }

    public void aplicarDanioExplosion(Explosion explosion) {
        double distancia = explosion.getEpicentro().distanciaA(avion.getPosicion());

        if (distancia > 150) {
            return;
        }

        if (distancia >= 80 && distancia <= 150) {
            jugador.recibirDanio(20);
        } else if (distancia >= 20 && distancia < 80) {
            jugador.recibirDanio(40);
        } else {
            jugador.perderVida();
        }

        if (!jugador.estaVivo()) {
            avion.recibirDanio(100);
            enCurso = false;
        }
    }

    public void actualizarPuntosYVida(Explosion explosion) {
        double distancia = explosion.getEpicentro().distanciaA(avion.getPosicion());

        if (distancia > 150) {
            jugador.sumarPuntos(40);
        } else if (distancia >= 80 && distancia <= 150) {
            jugador.sumarPuntos(20);
        }

        while (jugador.getPuntos() >= proximaVidaExtra) {
            jugador.ganarVidaExtra();
            proximaVidaExtra += 1000;
        }
    }

    public boolean debeContinuar() {
        return enCurso && jugador.estaVivo() && avion.estaActivo();
    }

    public void terminar() {
        enCurso = false;

        System.out.println("Juego finalizado.");
        System.out.println("Jugador: " + jugador.getNombre());
        System.out.println("Puntos obtenidos: " + jugador.getPuntos());
        System.out.println("Vida actual: " + jugador.getVida());
        System.out.println("Vidas restantes: " + jugador.getVidasRestantes());
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Avion getAvion() {
        return avion;
    }

    public List<Misil> getMisiles() {
        return misiles;
    }

    public boolean isEnCurso() {
        return enCurso;
    }
}
