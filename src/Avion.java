// Avion controlado por el jugador. Es una EntidadVoladora que se desplaza por
// input. Segun la consigna el jugador solo esquiva: el avion no dispara.
public class Avion extends EntidadVoladora {

    public Avion(String id, Posicion posicion, double velocidad) {
        super(id, posicion, velocidad);
    }

    public void mover(double dx, double dy) {
        this.posicion.setX(this.posicion.getX() + dx);
        this.posicion.setY(this.posicion.getY() + dy);
    }

    // Reaparece en el centro del eje X tras perder una vida
    public void reaparecer() {
        this.posicion = new Posicion(Posicion.X_MAX / 2, this.posicion.getY());
    }
}
