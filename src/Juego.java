import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Jugador jugador;
    private Avion avion;
    private List<Misil> misiles;
    private boolean enCurso;

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
        // TODO: verificar posicion Y de cada misil y detonar si corresponde
    }

    public void aplicarDanioExplosion(Explosion explosion) {
        // TODO: calcular danio al avion y al jugador segun distancia a explosion
    }

    public void actualizarPuntosYVida(Explosion explosion) {
        // TODO: sumar/restar puntos y vida en base a resultado de explosion
    }

    public boolean debeContinuar() {
        // TODO: retornar true si jugador sigue vivo y tiene puntos suficientes
        return false;
    }

    public void terminar() {
        // TODO: finalizar el juego y mostrar resultado
    }

    public Jugador getJugador() { return jugador; }
    public Avion getAvion() { return avion; }
    public List<Misil> getMisiles() { return misiles; }
    public boolean isEnCurso() { return enCurso; }
}
