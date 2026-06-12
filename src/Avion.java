// Avion del jugador. Usa el mover(Direccion) por defecto de EntidadVoladora:
// puede desplazarse en las cuatro direcciones para esquivar.
public class Avion extends EntidadVoladora {

    public Avion(String id, Posicion posicion, double velocidad) {
        super(id, posicion, velocidad);
    }

    // Reaparece en el centro del eje X tras perder una vida
    public void reaparecer() {
        this.posicion = new Posicion(Posicion.X_MAX / 2, this.posicion.getY());
    }
}
