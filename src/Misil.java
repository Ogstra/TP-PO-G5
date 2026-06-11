public class Misil {
    private String id;
    private Posicion posicion;
    private double yDetonacion;
    private boolean detonado;

    public Misil(String id, Posicion posicionInicial, double yDetonacion) {
        this.id = id;
        this.posicion = posicionInicial;
        this.yDetonacion = yDetonacion;
        this.detonado = false;
    }

    public void lanzar() {
        this.posicion.setY(this.yDetonacion);
    }

    // Mueve el misil hacia abajo (Y aumenta en pantalla)
    public void avanzar(double velocidad) {
        this.posicion.setY(this.posicion.getY() + velocidad);
    }

    public boolean verificarDetonacionPorY() {
        return posicion.getY() >= yDetonacion;
    }

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
