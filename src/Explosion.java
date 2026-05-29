public class Explosion {
    private Posicion epicentro;
    private double radioEfecto;
    private double potencia;

    public Explosion(Posicion epicentro, double radioEfecto, double potencia) {
        this.epicentro = epicentro;
        this.radioEfecto = radioEfecto;
        this.potencia = potencia;
    }

    public double calcularDanioADistancia(Posicion objetivo) {
        // TODO: calcular danio basado en distancia entre epicentro y objetivo
        return 0;
    }

    public boolean afectaA(Posicion objetivo) {
        // TODO: retornar true si objetivo esta dentro del radioEfecto
        return false;
    }

    public Posicion getEpicentro() { return epicentro; }
    public double getRadioEfecto() { return radioEfecto; }
    public double getPotencia() { return potencia; }
}
