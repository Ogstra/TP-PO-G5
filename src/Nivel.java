public class Nivel {
    private static final double INCREMENTO_POR_NIVEL = 0.15;

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
        this.velocidadDrones = velocidadBase * factor;
        this.velocidadMisiles = velocidadBase * factor;
        this.frecuenciaDisparo = frecuenciaBase * factor;
    }

    public Nivel siguiente() {
        return new Nivel(numero + 1, velocidadBase, frecuenciaBase);
    }

    public int getNumero() { return numero; }
    public double getVelocidadDrones() { return velocidadDrones; }
    public double getVelocidadMisiles() { return velocidadMisiles; }
    public double getFrecuenciaDisparo() { return frecuenciaDisparo; }
}
