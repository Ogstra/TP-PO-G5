// Clase abstracta: misil generico. Es una EntidadVoladora; concreta su
// movimiento y condicion de detonacion en MisilJugador y MisilEnemigo.
public abstract class Misil extends EntidadVoladora {
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
            return new Explosion(this.posicion, 100, 50);
        }
        return null;
    }

    // Detonacion forzada por colision (misil del jugador contra un dron)
    public Explosion detonarPorColision() {
        this.detonado = true;
        return new Explosion(this.posicion, 60, 0);
    }

    public boolean estaDetonado() { return detonado; }
}
