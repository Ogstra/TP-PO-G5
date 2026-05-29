public class Avion {
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
        // TODO: actualizar posicion segun movimiento
    }

    public Misil generarMisil() {
        // TODO: crear y retornar un nuevo misil desde la posicion del avion
        return null;
    }

    public void lanzarMisil(Misil misil) {
        // TODO: lanzar el misil generado
    }

    public void recibirDanio(double danio) {
        // TODO: aplicar danio al avion
    }

    public boolean estaActivo() { return activo; }
    public Posicion getPosicion() { return posicion; }
    public String getId() { return id; }
    public double getVelocidad() { return velocidad; }
}
