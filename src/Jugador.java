public class Jugador {
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
        // TODO: incrementar puntos del jugador
    }

    public void restarPuntos(int cantidad) {
        // TODO: decrementar puntos (minimo 0)
    }

    public void recibirDanio(double danio) {
        // TODO: restar vida segun danio recibido
    }

    public void perderVida() {
        // TODO: decrementar vidasRestantes, resetear vida
    }

    public boolean estaVivo() {
        // TODO: retornar true si vida > 0 o vidasRestantes > 0
        return false;
    }

    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    public int getVida() { return vida; }
    public int getVidasRestantes() { return vidasRestantes; }
}
