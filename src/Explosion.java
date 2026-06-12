// Explosion de un misil. Solo guarda epicentro y radio para el calculo de
// distancia al avion y para la animacion visual (que dura unos frames).
public class Explosion {
    private Posicion epicentro;
    private double radioEfecto;
    private int framesRestantes = 15;

    public Explosion(Posicion epicentro, double radioEfecto) {
        this.epicentro = epicentro;
        this.radioEfecto = radioEfecto;
    }

    // Envejece la animacion un frame
    public void envejecer() { framesRestantes--; }
    public boolean estaViva() { return framesRestantes > 0; }
    public int getFramesRestantes() { return framesRestantes; }

    public Posicion getEpicentro() { return epicentro; }
    public double getRadioEfecto() { return radioEfecto; }
}
