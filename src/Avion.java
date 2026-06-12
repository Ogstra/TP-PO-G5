// Avion del jugador. Usa el mover(Direccion) por defecto de EntidadVoladora:
// puede desplazarse en las cuatro direcciones. Ademas dispara misiles.
public class Avion extends EntidadVoladora {

    public Avion(String id, Posicion posicion, double velocidad) {
        super(id, posicion, velocidad);
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
