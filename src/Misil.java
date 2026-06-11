// Clase abstracta: generaliza el comportamiento comun de los misiles.
// No se puede instanciar; se concreta en MisilJugador y MisilEnemigo.
// El movimiento y la condicion de detonacion por altura son polimorficos.
public abstract class Misil implements Posicionable {
    protected String id;
    protected Posicion posicion;
    protected double velocidad;
    protected boolean detonado;

    protected Misil(String id, Posicion posicionInicial, double velocidad) {
        this.id = id;
        this.posicion = posicionInicial;
        this.velocidad = velocidad;
        this.detonado = false;
    }

    // Cada subtipo decide su direccion de avance
    public abstract void avanzar();

    // Cada subtipo decide si detona automaticamente por su altura
    protected abstract boolean alcanzoDetonacion();

    // Indica si el misil daña al avion del jugador (enemigo) o ataca drones (jugador)
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
    public Posicion getPosicion() { return posicion; }
    public String getId() { return id; }
}
