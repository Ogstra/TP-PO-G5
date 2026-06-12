// Clase abstracta: misil generico. Es una EntidadVoladora que se desplaza sola
// (Movible). Concreta su direccion y su condicion de detonacion en las
// subclases MisilEnemigo (cae, daña al jugador) y MisilJugador (sube, mata drones).
public abstract class Misil extends EntidadVoladora implements Movible {
    protected boolean detonado;

    protected Misil(String id, Posicion posicionInicial, double velocidad) {
        super(id, posicionInicial, velocidad);
        this.detonado = false;
    }

    // Cada subtipo decide si detona automaticamente por su altura
    protected abstract boolean alcanzoDetonacion();

    // Indica si el misil ataca drones (jugador) o daña al avion (enemigo)
    public abstract boolean esDelJugador();

    // Detonacion automatica por altura. Devuelve null si todavia no corresponde.
    public Explosion detonar() {
        if (alcanzoDetonacion()) {
            this.detonado = true;
            return new Explosion(this.posicion, 100);
        }
        return null;
    }

    // Detonacion forzada por colision (misil del jugador contra un dron)
    public Explosion detonarPorColision() {
        this.detonado = true;
        return new Explosion(this.posicion, 60);
    }

    public boolean estaDetonado() { return detonado; }
}
