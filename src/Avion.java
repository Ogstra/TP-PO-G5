// Avion controlado por el jugador. Es una EntidadVoladora y ademas puede
// recibir dano (IDanable).
public class Avion extends EntidadVoladora implements IDanable {
    private boolean activo;

    public Avion(String id, Posicion posicion, double velocidad) {
        super(id, posicion, velocidad);
        this.activo = true;
    }

    // Movimiento controlado por el jugador (desplazamiento libre en 2D)
    public void mover(double dx, double dy) {
        this.posicion.setX(this.posicion.getX() + dx);
        this.posicion.setY(this.posicion.getY() + dy);
    }

    // mover() sin parametros de EntidadVoladora: el avion se mueve por input,
    // no de forma automatica, por eso aqui no hace nada.
    @Override
    public void mover() {
        // no-op: el desplazamiento real ocurre en mover(dx, dy)
    }

    public Misil generarMisil() {
        // El misil del jugador viaja al doble de la velocidad del avion
        return new MisilJugador(this.id, new Posicion(posicion.getX(), posicion.getY()), velocidad * 2);
    }

    @Override
    public void recibirDanio(String tipo, double valor) {
        if (valor <= 0) {
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
}
