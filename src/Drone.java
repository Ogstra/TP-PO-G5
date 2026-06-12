// Dron enemigo. Es una EntidadVoladora que cruza la pantalla en horizontal
// y lanza misiles que descienden.
public class Drone extends EntidadVoladora implements Movible {
    private boolean moviendoseADerecha;

    public Drone(String id, Posicion posicion, double velocidad, boolean moviendoseADerecha) {
        super(id, posicion, velocidad);
        this.moviendoseADerecha = moviendoseADerecha;
    }

    @Override
    public void mover() {
        if (moviendoseADerecha) {
            posicion.setX(posicion.getX() + velocidad);
        } else {
            posicion.setX(posicion.getX() - velocidad);
        }
    }

    public Misil lanzarMisil(double velocidadMisil) {
        double minY = posicion.getY() + 50;
        double maxY = Posicion.Y_MAX - 10;
        double yDetonacion = minY < maxY ? minY + Math.random() * (maxY - minY) : maxY;
        return new Misil(this.id, new Posicion(posicion.getX(), posicion.getY()), yDetonacion, velocidadMisil);
    }

    public boolean completoRecorrido() {
        if (moviendoseADerecha) {
            return posicion.getX() >= Posicion.X_MAX;
        } else {
            return posicion.getX() <= Posicion.X_MIN;
        }
    }

    // Retorna true con probabilidad `frecuencia` (0.0 a 1.0)
    public boolean puedeLanzar(double frecuencia) {
        return Math.random() < frecuencia;
    }
}
