import java.util.ArrayList;
import java.util.List;

// Logica de negocio del juego (sin nada de interfaz grafica).
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

    public boolean debeContinuar() {
        return enCurso && jugador.estaVivo();
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
        List<Misil> nuevos = escuadron.procesarLanzamientos(nivel.getFrecuenciaDisparo(), nivel.getVelocidadMisiles());
        misiles.addAll(nuevos);
    }

    // Mueve los misiles enemigos, los detona al alcanzar su altitud y aplica dano
    public void procesarCaidaMisiles() {
        for (int i = misiles.size() - 1; i >= 0; i--) {
            Misil misil = misiles.get(i);
            if (misil.estaDetonado()) {
                misiles.remove(i);
                continue;
            }
            misil.mover();

            Explosion explosion = misil.detonar();
            if (explosion != null) {
                double distancia = explosion.getEpicentro().distanciaA(avion.getPosicion());
                aplicarDanioSegunDistancia(distancia);
                misiles.remove(i);
                continue;
            }

            // Eliminar misiles que salieron de pantalla sin detonar
            double y = misil.getPosicion().getY();
            if (y >= Posicion.Y_MAX || y <= Posicion.Y_MIN) {
                misiles.remove(i);
            }
        }
    }

    // El avion destruye por contacto a los drones, pero el choque le cuesta 33% de energia
    public void procesarColisiones() {
        if (escuadron == null) return;
        for (Drone drone : escuadron.getDronesActivos()) {
            if (avion.getPosicion().distanciaA(drone.getPosicion()) < 25) {
                drone.recibirDanio(1);
                int vidasAntes = jugador.getVidasRestantes();
                jugador.recibirDanio(Jugador.VIDA_MAX * 0.33);
                manejarPerdidaDeVida(vidasAntes);
                break; // un choque por frame basta
            }
        }
    }

    // Si el jugador perdio una vida y sigue vivo, el avion reaparece en el centro
    private void manejarPerdidaDeVida(int vidasAntes) {
        if (jugador.getVidasRestantes() < vidasAntes && jugador.estaVivo()) {
            avion.reaparecer();
        }
    }

    // Aplica dano segun 4 rangos de distancia definidos en el requerimiento
    public void aplicarDanioSegunDistancia(double distancia) {
        int vidasAntes = jugador.getVidasRestantes();

        if (distancia > 150) {
            jugador.sumarPuntos(40);
        } else if (distancia >= 80) {
            jugador.sumarPuntos(20);
            jugador.recibirDanio(Jugador.VIDA_MAX * 0.2);
        } else if (distancia >= 20) {
            jugador.recibirDanio(Jugador.VIDA_MAX * 0.4);
        } else {
            jugador.perderVida();
        }

        manejarPerdidaDeVida(vidasAntes);

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
        return escuadron != null && escuadron.estaCompleto();
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
        this.escuadron = new Escuadron(nivel.getVelocidadDrones());
    }

    public Jugador getJugador() { return jugador; }
    public Avion getAvion() { return avion; }
}
