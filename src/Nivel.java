public class Nivel {
    private static final double INCREMENTO_POR_NIVEL = 0.15;

    private int numero;
    private double velocidadDrones;
    private double velocidadMisiles;
    private double frecuenciaDisparo;

    public Nivel(int numero, double velocidadBase, double frecuenciaBase) {
        this.numero = numero;
        double factor = Math.pow(1 + INCREMENTO_POR_NIVEL, numero - 1);
        this.velocidadDrones = velocidadBase * factor;
        this.velocidadMisiles = velocidadBase * factor;
        this.frecuenciaDisparo = frecuenciaBase * factor;
    }

    // Crea el siguiente nivel con velocidades incrementadas
    public Nivel siguiente() {
        // TODO: retornar new Nivel(numero + 1, ...) con valores base del nivel actual
        return null;
    }

    public int getNumero() { return numero; }
    public double getVelocidadDrones() { return velocidadDrones; }
    public double getVelocidadMisiles() { return velocidadMisiles; }
    public double getFrecuenciaDisparo() { return frecuenciaDisparo; }
}
