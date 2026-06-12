// Misil disparado por el avion: solo se mueve hacia ARRIBA y explota unicamente
// al impactar un dron (no detona por altura).
public class MisilJugador extends Misil {

    public MisilJugador(String id, Posicion posicionInicial, double velocidad) {
        super(id, posicionInicial, velocidad);
    }

    @Override
    public void mover(Direccion direccion) {
        if (direccion == Direccion.ARRIBA) {
            super.mover(direccion);
        }
    }

    @Override
    public Direccion getDireccion() { return Direccion.ARRIBA; }

    @Override
    protected boolean alcanzoDetonacion() { return false; }

    @Override
    public boolean esDelJugador() { return true; }
}
