// Avion controlado por el jugador. Es una EntidadVoladora que se desplaza por
// input y puede disparar misiles hacia arriba.
public class Avion extends EntidadVoladora {

    public Avion(String id, Posicion posicion, double velocidad) {
        super(id, posicion, velocidad);
    }

    public void mover(double dx, double dy) {
        this.posicion.setX(this.posicion.getX() + dx);
        this.posicion.setY(this.posicion.getY() + dy);
    }

    public Misil generarMisil() {
        // El misil del jugador viaja al doble de la velocidad del avion
        return new MisilJugador(this.id, new Posicion(posicion.getX(), posicion.getY()), velocidad * 2);
    }

    // Reaparece en el centro del eje X tras perder una vida
    public void reaparecer() {
        this.posicion = new Posicion(Posicion.X_MAX / 2, this.posicion.getY());
    }
}
