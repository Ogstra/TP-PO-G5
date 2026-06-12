// Explosion de un misil. En la capa de negocio solo importa su epicentro,
// que sirve para calcular la distancia al avion y el dano resultante.
public class Explosion {
    private Posicion epicentro;

    public Explosion(Posicion epicentro) {
        this.epicentro = epicentro;
    }

    public Posicion getEpicentro() { return epicentro; }
}
