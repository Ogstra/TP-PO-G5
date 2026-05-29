public class Misil {
    private String id;
    private Posicion posicion;
    private double altitudDetonacion;
    private boolean detonado;

    public Misil(String id, Posicion posicionInicial, double altitudDetonacion) {
        this.id = id;
        this.posicion = posicionInicial;
        this.altitudDetonacion = altitudDetonacion;
        this.detonado = false;
    }

    public void lanzar() {
        this.posicion.setAltitud(this.altitudDetonacion);
    }

    public boolean verificarDetonacionPorAltitud() {
        return posicion.getAltitud() <= altitudDetonacion;
    }

    public Explosion detonar() {
        if (verificarDetonacionPorAltitud()) {
            this.detonado = true;
            return new Explosion(this.posicion, 100, 50); // Ejemplo de radio y potencia
        }
        return null;
    }

    public boolean estaDetonado() { return detonado; }
    public Posicion getPosicion() { return posicion; }
    public double getAltitudDetonacion() { return altitudDetonacion; }
    public String getId() { return id; }
}
