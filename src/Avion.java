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
        this.posicion.setX(this.posicion.getX() + dx);
        this.posicion.setY(this.posicion.getY() + dy);
    }

    public Misil generarMisil() {
        Posicion posicionMisil = new Posicion(
                this.posicion.getX(),
                this.posicion.getY()
        );

        return new Misil(this.id, posicionMisil, this.velocidad);
    }

    public void lanzarMisil(Misil misil) {
        if (misil != null) {
            misil.lanzar();
        }
    }

    public void recibirDanio(double danio) {
        if (danio <= 0) {
            return;
        }

        this.activo = false;
    }

    public boolean estaActivo() { return activo; }
    public Posicion getPosicion() { return posicion; }
    public String getId() { return id; }
    public double getVelocidad() { return velocidad; }
}
