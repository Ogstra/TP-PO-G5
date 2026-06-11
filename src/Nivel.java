public class Nivel {
    private static final double INCREMENTO_POR_NIVEL = 0.15;
    // Topes para que el juego siga siendo jugable en niveles altos
    private static final double VELOCIDAD_MAX = 12.0;
    private static final double FRECUENCIA_MAX = 0.08;

    private int numero;
    private double velocidadBase;
    private double frecuenciaBase;
    private double velocidadDrones;
    private double velocidadMisiles;
    private double frecuenciaDisparo;

    public Nivel(int numero, double velocidadBase, double frecuenciaBase) {
        this.numero = numero;
        this.velocidadBase = velocidadBase;
        this.frecuenciaBase = frecuenciaBase;
        double factor = Math.pow(1 + INCREMENTO_POR_NIVEL, numero - 1);
        this.velocidadDrones = Math.min(velocidadBase * factor, VELOCIDAD_MAX);
        this.velocidadMisiles = Math.min(velocidadBase * factor, VELOCIDAD_MAX);
        this.frecuenciaDisparo = Math.min(frecuenciaBase * factor, FRECUENCIA_MAX);
    }

    public Nivel siguiente() {
        return new Nivel(numero + 1, velocidadBase, frecuenciaBase);
    }

    public int getNumero() { return numero; }
    public double getVelocidadDrones() { return velocidadDrones; }
    public double getVelocidadMisiles() { return velocidadMisiles; }
    public double getFrecuenciaDisparo() { return frecuenciaDisparo; }
}
