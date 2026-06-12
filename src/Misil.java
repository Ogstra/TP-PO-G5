// Misil lanzado por un dron. Restringe mover(): solo se desplaza hacia ABAJO.
// Detona automaticamente al alcanzar su altitud de detonacion.
public class Misil extends EntidadVoladora {
    private double yDetonacion;
    private boolean detonado;

    public Misil(String id, Posicion posicionInicial, double yDetonacion, double velocidad) {
        super(id, posicionInicial, velocidad);
        this.yDetonacion = yDetonacion;
        this.detonado = false;
    }

    @Override
    public void mover(Direccion direccion) {
        // El misil solo cae; ignora cualquier otra direccion
        if (direccion == Direccion.ABAJO) {
            super.mover(direccion);
        }
    }

    private boolean debeDetonar() {
        return posicion.getY() >= yDetonacion;
    }

    public Explosion detonar() {
        if (debeDetonar()) {
            this.detonado = true;
            return new Explosion(this.posicion);
        }
        return null;
    }

    public boolean estaDetonado() { return detonado; }
}
