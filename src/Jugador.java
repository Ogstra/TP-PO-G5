public class Jugador implements Danable {
    public static final int ENERGIA_MAX = 100;

    private String nombre;
    private int puntos;
    private int energia;
    private int vidasRestantes;

    public Jugador(String nombre, int energiaInicial, int vidasRestantes) {
        this.nombre = nombre;
        this.energia = energiaInicial;
        this.vidasRestantes = vidasRestantes;
        this.puntos = 0;
    }

    public void sumarPuntos(int cantidad) {
        this.puntos += cantidad;
    }

    @Override
    public void recibirDanio(double valor) {
        if (valor <= 0) {
            return;
        }

        this.energia -= (int) valor;
        if (this.energia <= 0) {
            perderVida();
        }
    }

    public void perderVida() {
        if (vidasRestantes > 0) {
            vidasRestantes--;
            energia = ENERGIA_MAX;
        } else {
            energia = 0;
        }
    }

    public void ganarVidaExtra() {
        this.vidasRestantes++;
    }

    public boolean estaVivo() {
        return energia > 0 || vidasRestantes > 0;
    }

    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    public int getEnergia() { return energia; }
    public int getVidasRestantes() { return vidasRestantes; }
}
