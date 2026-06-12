// Avion controlado por el jugador. Es una EntidadVoladora que se desplaza por
// input. Segun la consigna el jugador solo esquiva: el avion no dispara.
public class Avion extends EntidadVoladora {
    private boolean activo;

    public Avion(String id, Posicion posicion, double velocidad) {
        super(id, posicion, velocidad);
        this.activo = true;
    }

    // Movimiento controlado por el jugador (izq/der y altitud)
    public void mover(double dx, double dy) {
        this.posicion.setX(this.posicion.getX() + dx);
        this.posicion.setY(this.posicion.getY() + dy);
    }

    // mover() de EntidadVoladora: el avion se mueve por input, no automatico
    @Override
    public void mover() {
        // no-op: el desplazamiento real ocurre en mover(dx, dy)
    }

    // Reaparece en el centro del eje X tras perder una vida
    public void reaparecer() {
        this.posicion = new Posicion(Posicion.X_MAX / 2, this.posicion.getY());
        this.activo = true;
    }

    public boolean estaActivo() { return activo; }
}
