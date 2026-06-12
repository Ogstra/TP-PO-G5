// Clase abstracta: misil generico. Hereda mover(Direccion) de EntidadVoladora
// pero cada subtipo lo restringe a su unica direccion (MisilEnemigo ABAJO,
// MisilJugador ARRIBA) y expone esa direccion via getDireccion().
public abstract class Misil extends EntidadVoladora {
    protected boolean detonado;

    protected Misil(String id, Posicion posicionInicial, double velocidad) {
        super(id, posicionInicial, velocidad);
        this.detonado = false;
    }

    // Direccion fija en la que viaja este misil
    public abstract Direccion getDireccion();

    // Decide si detona automaticamente por su altura
    protected abstract boolean alcanzoDetonacion();

    // Indica si el misil ataca drones (jugador) o daña al avion (enemigo)
    public abstract boolean esDelJugador();

    public Explosion detonar() {
        if (alcanzoDetonacion()) {
            this.detonado = true;
            return new Explosion(this.posicion, 100);
        }
        return null;
    }

    public Explosion detonarPorColision() {
        this.detonado = true;
        return new Explosion(this.posicion, 60);
    }

    public boolean estaDetonado() { return detonado; }
}
