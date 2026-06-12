// Clase abstracta: estado comun de toda entidad del espacio aereo.
// Implementa Movible con un movimiento por defecto en cualquier direccion;
// las subclases que lo necesiten lo redefinen para restringir direcciones
// (el dron solo horizontal, el misil solo vertical). El avion usa el default.
public abstract class EntidadVoladora implements Movible {
    protected String id;
    protected Posicion posicion;
    protected double velocidad;

    protected EntidadVoladora(String id, Posicion posicion, double velocidad) {
        this.id = id;
        this.posicion = posicion;
        this.velocidad = velocidad;
    }

    @Override
    public void mover(Direccion direccion) {
        switch (direccion) {
            case ARRIBA:    posicion.setY(posicion.getY() - velocidad); break;
            case ABAJO:     posicion.setY(posicion.getY() + velocidad); break;
            case IZQUIERDA: posicion.setX(posicion.getX() - velocidad); break;
            case DERECHA:   posicion.setX(posicion.getX() + velocidad); break;
        }
    }

    public Posicion getPosicion() { return posicion; }
    public double getVelocidad() { return velocidad; }
    public String getId() { return id; }
}
