import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Jugador jugador;
    private Avion avion;
    private List<Misil> misiles;
    private boolean enCurso;
    private int proximaVidaExtra = 1000;
    private Escuadron escuadron;
    private Nivel nivel;

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

    // Mueve drones activos y agrega nuevos misiles enemigos a la lista
    public void procesarEscuadron() {
        escuadron.activarProximoDrone();
        escuadron.procesarMovimiento();
        List<Misil> nuevos = escuadron.procesarLanzamientos(nivel.getFrecuenciaDisparo());
        misiles.addAll(nuevos);
    }

    // Mueve todos los misiles enemigos hacia abajo (caen desde drones)
    public void procesarCaidaMisiles() {
        for (int i = misiles.size() - 1; i >= 0; i--) {
            Misil misil = misiles.get(i);
            if (!misil.estaDetonado()) {
                misil.avanzar(nivel.getVelocidadMisiles());
                Explosion explosion = misil.detonar();
                if (explosion != null) {
                    double distancia = explosion.getEpicentro().distanciaA(avion.getPosicion());
                    aplicarDanioSegunDistancia(distancia);
                    misiles.remove(i);
                }
            } else {
                misiles.remove(i);
            }
        }
    }

    // Aplica dano segun 4 rangos de distancia definidos en el requerimiento
    public void aplicarDanioSegunDistancia(double distancia) {
        if (distancia > 150) {
            jugador.sumarPuntos(40);
        } else if (distancia >= 80) {
            jugador.sumarPuntos(20);
            jugador.recibirDanio(jugador.getVida() * 0.2);
        } else if (distancia >= 20) {
            jugador.recibirDanio(jugador.getVida() * 0.4);
        } else {
            jugador.perderVida();
        }

        while (jugador.getPuntos() >= proximaVidaExtra) {
            jugador.ganarVidaExtra();
            proximaVidaExtra += 1000;
        }
    }

    public void avanzarNivel() {
        nivel = nivel.siguiente();
        jugador.sumarPuntos(300);
        escuadron = new Escuadron(nivel.getVelocidadDrones());
    }

    public boolean nivelCompleto() {
        return escuadron.estaCompleto();
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
