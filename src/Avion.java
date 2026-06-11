public class Avion implements Posicionable {
    private String id;
    private Posicion posicion;
    private double velocidad;
    private boolean activo;

    public Avion(String id, Posicion posicion, double velocidad) {
        this.id = id;
        this.posicion = posicion;
        this.velocidad = velocidad;
        this.activo = true;
    }

    public void mover(double dx, double dy) {
        this.posicion.setX(this.posicion.getX() + dx);
        this.posicion.setY(this.posicion.getY() + dy);
    }

    public Misil generarMisil() {
        // El misil del jugador viaja al doble de la velocidad del avion
        return new MisilJugador(this.id, new Posicion(posicion.getX(), posicion.getY()), velocidad * 2);
    }

    public void recibirDanio(double danio) {
        if (danio <= 0) {
            return;
        }

        this.activo = false;
    }

    // Reaparece en el centro del eje X tras perder una vida
    public void reaparecer() {
        this.posicion = new Posicion(Posicion.X_MAX / 2, this.posicion.getY());
        this.activo = true;
    }

    public boolean estaActivo() { return activo; }
    public Posicion getPosicion() { return posicion; }
    public String getId() { return id; }
    public double getVelocidad() { return velocidad; }
}
