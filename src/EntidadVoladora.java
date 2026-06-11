// Clase abstracta: generaliza el estado comun de toda entidad que se desplaza
// por el espacio aereo (avion, drones, misiles). No se puede instanciar.
// Reemplaza a la antigua interfaz Posicionable: ahora la posicion es estado
// compartido por herencia.
public abstract class EntidadVoladora {
    protected String id;
    protected Posicion posicion;
    protected double velocidad;

    protected EntidadVoladora(String id, Posicion posicion, double velocidad) {
        this.id = id;
        this.posicion = posicion;
        this.velocidad = velocidad;
    }

    // Cada entidad concreta define como se desplaza
    public abstract void mover();

    public Posicion getPosicion() { return posicion; }
    public double getVelocidad() { return velocidad; }
    public String getId() { return id; }
}
