import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Jugador jugador;
    private Avion avion;
    private List<Misil> misiles;
    private List<Explosion> explosionesRecientes = new ArrayList<>();
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
        List<Misil> nuevos = escuadron.procesarLanzamientos(nivel.getFrecuenciaDisparo(), nivel.getVelocidadMisiles());
        misiles.addAll(nuevos);
    }

    // Mueve todos los misiles y procesa detonaciones
    public void procesarCaidaMisiles() {
        // envejecer explosiones de frames anteriores
        for (int i = explosionesRecientes.size() - 1; i >= 0; i--) {
            explosionesRecientes.get(i).envejecer();
            if (!explosionesRecientes.get(i).estaViva()) {
                explosionesRecientes.remove(i);
            }
        }
        for (int i = misiles.size() - 1; i >= 0; i--) {
            Misil misil = misiles.get(i);
            if (misil.estaDetonado()) {
                misiles.remove(i);
                continue;
            }
            misil.avanzar(); // cada subtipo conoce su direccion y velocidad

            // Solo los misiles enemigos detonan por altura (los del jugador devuelven null)
            Explosion explosion = misil.detonar();
            if (explosion != null) {
                explosionesRecientes.add(explosion);
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

    // Detecta impactos de misiles del jugador contra drones activos
    public void procesarColisiones() {
        if (escuadron == null) return;
        List<Drone> drones = escuadron.getDronesActivos();
        for (int i = misiles.size() - 1; i >= 0; i--) {
            Misil misil = misiles.get(i);
            if (!misil.esDelJugador() || misil.estaDetonado()) continue;
            for (int j = drones.size() - 1; j >= 0; j--) {
                Drone drone = drones.get(j);
                if (drone.getPosicion().distanciaA(misil.getPosicion()) < 25) {
                    explosionesRecientes.add(misil.detonarPorColision());
                    escuadron.destruir(drone);
                    misiles.remove(i);
                    break;
                }
            }
        }
    }

    public List<Explosion> getExplosionesRecientes() { return explosionesRecientes; }

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

        // Si perdio una vida y sigue vivo: explota el avion y reaparece en el centro
        if (jugador.getVidasRestantes() < vidasAntes && jugador.estaVivo()) {
            explosionesRecientes.add(new Explosion(avion.getPosicion(), 80, 0));
            avion.reaparecer();
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
        return escuadron != null && escuadron.estaCompleto();
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
        this.escuadron = new Escuadron(nivel.getVelocidadDrones());
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

    public List<Drone> getDronesActivos() {
        return escuadron != null ? escuadron.getDronesActivos() : new ArrayList<>();
    }

    public boolean isEnCurso() {
        return enCurso;
    }
}
