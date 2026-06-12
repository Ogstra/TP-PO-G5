import java.util.ArrayList;
import java.util.List;

// Escuadron de 10 drones que cruzan la pantalla. Nunca mas de 4 activos a la vez.
public class Escuadron {
    private static final int TOTAL_DRONES = 10;
    private static final int MAX_ACTIVOS = 4;

    private List<Drone> drones = new ArrayList<>();
    private List<Drone> dronesActivos = new ArrayList<>();
    private int indiceProximo = 0;

    public Escuadron(double velocidad) {
        for (int i = 0; i < TOTAL_DRONES; i++) {
            boolean derecha = Math.random() < 0.5;
            Direccion dir = derecha ? Direccion.DERECHA : Direccion.IZQUIERDA;
            double x = derecha ? Posicion.X_MIN : Posicion.X_MAX;
            double y = Math.random() * (Posicion.Y_MAX / 3);
            drones.add(new Drone("D" + i, new Posicion(x, y), velocidad, dir));
        }
    }

    public void activarProximoDrone() {
        if (dronesActivos.size() < MAX_ACTIVOS && indiceProximo < drones.size()) {
            dronesActivos.add(drones.get(indiceProximo));
            indiceProximo++;
        }
    }

    public void procesarMovimiento() {
        for (int i = dronesActivos.size() - 1; i >= 0; i--) {
            Drone drone = dronesActivos.get(i);
            drone.mover(drone.getDireccion());
            if (drone.completoRecorrido() || !drone.estaVivo()) {
                dronesActivos.remove(i);
            }
        }
    }

    public List<Misil> procesarLanzamientos(double frecuencia, double velocidadMisil) {
        List<Misil> nuevos = new ArrayList<>();
        for (Drone drone : dronesActivos) {
            if (drone.puedeLanzar(frecuencia)) {
                nuevos.add(drone.lanzarMisil(velocidadMisil));
            }
        }
        return nuevos;
    }

    public boolean estaCompleto() {
        return indiceProximo >= drones.size() && dronesActivos.isEmpty();
    }

    public List<Drone> getDronesActivos() { return dronesActivos; }
}
