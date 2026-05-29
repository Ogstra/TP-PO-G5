public class Posicion {
    private double x;
    private double y;
    private double altitud;

    public Posicion(double x, double y, double altitud) {
        this.x = x;
        this.y = y;
        this.altitud = altitud;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getAltitud() { return altitud; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setAltitud(double altitud) { this.altitud = altitud; }

    public double distanciaA(Posicion otra) {
        double dx = this.x - otra.x;
        double dy = this.y - otra.y;
        double dz = this.altitud - otra.altitud;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
