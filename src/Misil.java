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
        // TODO: iniciar trayectoria del misil
    }

    public boolean verificarDetonacionPorAltitud() {
        // TODO: retornar true si posicion.getAltitud() <= altitudDetonacion
        return false;
    }

    public Explosion detonar() {
        // TODO: marcar como detonado y crear explosion en posicion actual
        return null;
    }

    public boolean estaDetonado() { return detonado; }
    public Posicion getPosicion() { return posicion; }
    public double getAltitudDetonacion() { return altitudDetonacion; }
    public String getId() { return id; }
}
