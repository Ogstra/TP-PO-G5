// Misil disparado por el avion: asciende y solo explota al impactar un dron
// (no detona por altura). Se descarta en silencio si sale por el borde superior.
public class MisilJugador extends Misil {

    public MisilJugador(String id, Posicion posicionInicial, double velocidad) {
        super(id, posicionInicial, velocidad);
    }

    @Override
    public void avanzar() {
        posicion.setY(posicion.getY() - velocidad); // sube (Y disminuye en pantalla)
    }

    @Override
    protected boolean alcanzoDetonacion() {
        return false; // solo detona por colision con un dron
    }

    @Override
    public boolean esDelJugador() {
        return true;
    }
}
