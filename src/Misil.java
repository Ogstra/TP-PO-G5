// Misil lanzado por un dron. Es una EntidadVoladora que desciende en linea
// recta y detona automaticamente al alcanzar su altitud de detonacion.
public class Misil extends EntidadVoladora {
    private double yDetonacion;
    private boolean detonado;

    public Misil(String id, Posicion posicionInicial, double yDetonacion, double velocidad) {
        super(id, posicionInicial, velocidad);
        this.yDetonacion = yDetonacion;
        this.detonado = false;
    }

    @Override
    public void mover() {
        posicion.setY(posicion.getY() + velocidad); // desciende (Y aumenta en pantalla)
    }

    public boolean debeDetonar() {
        return posicion.getY() >= yDetonacion;
    }

    // Detona si alcanzo su altitud. Devuelve la explosion o null si todavia no.
    public Explosion detonar() {
        if (debeDetonar()) {
            this.detonado = true;
            return new Explosion(this.posicion, 100, 50);
        }
        return null;
    }

    public boolean estaDetonado() { return detonado; }
    public double getYDetonacion() { return yDetonacion; }
}
