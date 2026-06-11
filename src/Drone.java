public class Drone {
    private String id;
    private Posicion posicion;
    private double velocidad;
    private boolean activo;
    private boolean moviendoseADerecha;

    public Drone(String id, Posicion posicion, double velocidad, boolean moviendoseADerecha) {
        this.id = id;
        this.posicion = posicion;
        this.velocidad = velocidad;
        this.activo = true;
        this.moviendoseADerecha = moviendoseADerecha;
    }

    public void mover() {
        if (moviendoseADerecha) {
            posicion.setX(posicion.getX() + velocidad);
        } else {
            posicion.setX(posicion.getX() - velocidad);
        }
    }

    public Misil lanzarMisil() {
        double yDetonacion = Math.random() * (Posicion.Y_MAX - Posicion.Y_MIN) + Posicion.Y_MIN;
        return new Misil(this.id, new Posicion(posicion.getX(), posicion.getY()), yDetonacion);
    }

    public boolean completoRecorrido() {
        if  (moviendoseADerecha) {
            return posicion.getX() >= Posicion.X_MAX;
        } else {
            return posicion.getX() <= Posicion.X_MIN;
        }
    }

    public void activar() { this.activo = true; }
    public void desactivar() { this.activo = false; }

    // Retorna true con probabilidad `frecuencia` (0.0 a 1.0)
    public boolean puedeLanzar(double frecuencia) {
        return Math.random() < frecuencia;
    }

    public boolean estaActivo() { return activo; }
    public Posicion getPosicion() { return posicion; }
    public double getVelocidad() { return velocidad; }
    public String getId() { return id; }
}
