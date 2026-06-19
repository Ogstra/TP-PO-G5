public record Posicion(double x, double y) {
    public static final double X_MIN = 0;
    public static final double X_MAX = 1280;
    public static final double Y_MIN = 0;
    public static final double Y_MAX = 720;

    public Posicion {
        x = ajustarAlRango(x, X_MIN, X_MAX);
        y = ajustarAlRango(y, Y_MIN, Y_MAX);
    }

    public double distanciaA(Posicion otra) {
        double dx = this.x - otra.x;
        double dy = this.y - otra.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static double ajustarAlRango(double valor, double minimo, double maximo) {
        if (valor < minimo) return minimo;
        if (valor > maximo) return maximo;
        return valor;
    }
}
