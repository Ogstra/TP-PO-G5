public class Jugador implements IDanable {
    public static final int VIDA_MAX = 100;

    private String nombre;
    private int puntos;
    private int vida;
    private int vidasRestantes;

    public Jugador(String nombre, int vidaInicial, int vidasRestantes) {
        this.nombre = nombre;
        this.vida = vidaInicial;
        this.vidasRestantes = vidasRestantes;
        this.puntos = 0;
    }

    public void sumarPuntos(int cantidad) {
        this.puntos += cantidad;
    }

    @Override
    public void recibirDanio(String tipo, double valor) {
        if (valor <= 0) {
            return;
        }

        this.vida -= (int) valor;
        if (this.vida <= 0) {
            perderVida();
        }
    }

    public void perderVida() {
        if (vidasRestantes > 0) {
            vidasRestantes--;
            vida = VIDA_MAX; // Reiniciar energia al perder una vida
        } else {
            vida = 0; // El jugador ha perdido todas las vidas
        }
    }

    public void ganarVidaExtra() {
        this.vidasRestantes++;
    }

    public boolean estaVivo() {
        return vida > 0 || vidasRestantes > 0;
    }

    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    public int getVida() { return vida; }
    public int getVidasRestantes() { return vidasRestantes; }
}
