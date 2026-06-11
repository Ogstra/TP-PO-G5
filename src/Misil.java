public class Misil {
    private String id;
    private Posicion posicion;
    private double yDetonacion;
    private boolean detonado;
    private boolean esDelJugador;

    public Misil(String id, Posicion posicionInicial, double yDetonacion, boolean esDelJugador) {
        this.id = id;
        this.posicion = posicionInicial;
        this.yDetonacion = yDetonacion;
        this.detonado = false;
        this.esDelJugador = esDelJugador;
    }

    // Metodo reservado para futuros usos; el movimiento ocurre via avanzar()
    public void lanzar() {
        // no-op: el misil se mueve gradualmente via avanzar(), no se teleporta
    }

    // Mueve el misil: sube si es del jugador, baja si es enemigo
    public void avanzar(double velocidad) {
        if (esDelJugador) {
            this.posicion.setY(this.posicion.getY() - velocidad);
        } else {
            this.posicion.setY(this.posicion.getY() + velocidad);
        }
    }

    public boolean verificarDetonacionPorY() {
        // Misiles del jugador no detonan por altura: solo explotan al chocar un dron
        if (esDelJugador) {
            return false;
        }
        return posicion.getY() >= yDetonacion;
    }

    // Fuerza la detonacion (usado en colision misil-dron)
    public Explosion detonarPorColision() {
        this.detonado = true;
        return new Explosion(this.posicion, 60, 0);
    }

    public boolean esDelJugador() { return esDelJugador; }

    public Explosion detonar() {
        if (verificarDetonacionPorY()) {
            this.detonado = true;
            return new Explosion(this.posicion, 100, 50); // Ejemplo de radio y potencia
        }
        return null;
    }

    public boolean estaDetonado() { return detonado; }
    public Posicion getPosicion() { return posicion; }
    public double getYDetonacion() { return yDetonacion; }
    public String getId() { return id; }
}
