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
        double distancia = epicentro.distanciaA(objetivo);
        if (distancia > radioEfecto) {
            return 0;
        }

        // Danio proporcional a la potencia y decrece con la distancia
        return potencia * (1 - (distancia / radioEfecto));
    }

    public boolean afectaA(Posicion objetivo) {
        return epicentro.distanciaA(objetivo) <= radioEfecto;
    }

    public Posicion getEpicentro() { return epicentro; }
    public double getRadioEfecto() { return radioEfecto; }
    public double getPotencia() { return potencia; }
}
