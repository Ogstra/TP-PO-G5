// Misil lanzado por un dron: desciende en linea recta y detona al alcanzar
// la altitud aleatoria programada en el momento del lanzamiento.
public class MisilEnemigo extends Misil {
    private double yDetonacion;

    public MisilEnemigo(String id, Posicion posicionInicial, double yDetonacion, double velocidad) {
        super(id, posicionInicial, velocidad);
        this.yDetonacion = yDetonacion;
    }

    @Override
    public void avanzar() {
        posicion.setY(posicion.getY() + velocidad); // cae (Y aumenta en pantalla)
    }

    @Override
    protected boolean alcanzoDetonacion() {
        return posicion.getY() >= yDetonacion;
    }

    @Override
    public boolean esDelJugador() {
        return false;
    }

    public double getYDetonacion() { return yDetonacion; }
}
