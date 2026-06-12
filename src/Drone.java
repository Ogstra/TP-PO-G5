// Dron enemigo. Restringe mover(): solo se desplaza en su direccion horizontal
// (izquierda o derecha). Es Danable: cualquier impacto lo destruye.
public class Drone extends EntidadVoladora implements Danable {
    private Direccion direccion;
    private boolean vivo;

    public Drone(String id, Posicion posicion, double velocidad, Direccion direccion) {
        super(id, posicion, velocidad);
        this.direccion = direccion;
        this.vivo = true;
    }

    @Override
    public void mover(Direccion direccion) {
        // El dron solo se mueve en horizontal; ignora arriba/abajo
        if (direccion == Direccion.IZQUIERDA || direccion == Direccion.DERECHA) {
            super.mover(direccion);
        }
    }

    @Override
    public void recibirDanio(double valor) {
        if (valor > 0) {
            this.vivo = false;
        }
    }

    public Misil lanzarMisil(double velocidadMisil) {
        double minY = posicion.getY() + 50;
        double maxY = Posicion.Y_MAX - 10;
        double yDetonacion = minY < maxY ? minY + Math.random() * (maxY - minY) : maxY;
        return new MisilEnemigo(this.id, new Posicion(posicion.getX(), posicion.getY()), yDetonacion, velocidadMisil);
    }

    public boolean completoRecorrido() {
        if (direccion == Direccion.DERECHA) {
            return posicion.getX() >= Posicion.X_MAX;
        } else {
            return posicion.getX() <= Posicion.X_MIN;
        }
    }

    // Retorna true con probabilidad `frecuencia` (0.0 a 1.0)
    public boolean puedeLanzar(double frecuencia) {
        return Math.random() < frecuencia;
    }

    public Direccion getDireccion() { return direccion; }
    public boolean estaVivo() { return vivo; }
}
