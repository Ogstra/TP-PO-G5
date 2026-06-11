public class Drone {
    private String id;
    private Posicion posicion;
    private double velocidad;
    private boolean activo;
    private boolean moviendoseADerecha;

    public Drone(String id, Posicion posicion, double velocidad, boolean moviendoseADerecha) {
        this.id = id;
        this.posicion = posicion;
        this.velocidad = velocidad;
        this.activo = true;
        this.moviendoseADerecha = moviendoseADerecha;
    }

    // Mueve el drone horizontalmente segun su direccion
    public void mover() {
        // TODO: desplazar posicion X segun velocidad y direccion
    }

    // Genera un misil en la posicion actual del drone con altitud de detonacion aleatoria
    public Misil lanzarMisil() {
        // TODO: crear Misil con posicion actual y yDetonacion aleatoria entre 1200 y 4500 (mapeado a pixeles)
        return null;
    }

    // Verifica si el drone llego al extremo opuesto de la pantalla
    public boolean completoRecorrido() {
        // TODO: retornar true si salio del rango X de la pantalla
        return false;
    }

    public boolean estaActivo() { return activo; }
    public Posicion getPosicion() { return posicion; }
    public double getVelocidad() { return velocidad; }
    public String getId() { return id; }
    public void desactivar() { this.activo = false; }
}
