// Misil lanzado por un dron: solo se mueve hacia ABAJO y detona al alcanzar
// la altitud aleatoria programada en el lanzamiento.
public class MisilEnemigo extends Misil {
    private double yDetonacion;

    public MisilEnemigo(String id, Posicion posicionInicial, double yDetonacion, double velocidad) {
        super(id, posicionInicial, velocidad);
        this.yDetonacion = yDetonacion;
    }

    @Override
    public void mover(Direccion direccion) {
        if (direccion == Direccion.ABAJO) {
            super.mover(direccion);
        }
    }

    @Override
    public Direccion getDireccion() { return Direccion.ABAJO; }

    @Override
    protected boolean alcanzoDetonacion() {
        return posicion.getY() >= yDetonacion;
    }

    @Override
    public boolean esDelJugador() { return false; }
}
