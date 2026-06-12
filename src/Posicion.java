public class Posicion {
    public static final double X_MIN = 0;
    public static final double X_MAX = 1280;
    public static final double Y_MIN = 0;
    public static final double Y_MAX = 720;

    private double x;
    private double y;

    public Posicion(double x, double y) {
        this.x = ajustarAlRango(x, X_MIN, X_MAX);
        this.y = ajustarAlRango(y, Y_MIN, Y_MAX);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) {
        this.x = ajustarAlRango(x, X_MIN, X_MAX);
    }

    public void setY(double y) {
        this.y = ajustarAlRango(y, Y_MIN, Y_MAX);
    }

    public double distanciaA(Posicion otra) {
        double dx = this.x - otra.x;
        double dy = this.y - otra.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static double ajustarAlRango(double valor, double minimo, double maximo) {
        if (valor < minimo) {
            return minimo;
        }

        if (valor > maximo) {
            return maximo;
        }

        return valor;
    }
}
