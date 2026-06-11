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

    public void lanzar() {
        this.posicion.setY(this.yDetonacion);
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
        if (esDelJugador) {
            return posicion.getY() <= yDetonacion;
        }
        return posicion.getY() >= yDetonacion;
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
