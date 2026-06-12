import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private static final int TOTAL_DRONES = 20;
    private static final int MAX_ACTIVOS = 10;

    private List<Drone> drones = new ArrayList<>();
    private List<Drone> dronesActivos = new ArrayList<>();
    private int indiceProximo = 0;

    public Escuadron(double velocidad) {
        for (int i = 0; i < TOTAL_DRONES; i++) {
            boolean derecha = Math.random() < 0.5;
            double x = derecha ? Posicion.X_MIN : Posicion.X_MAX;
            double y = Math.random() * (Posicion.Y_MAX / 3);
            drones.add(new Drone("D" + i, new Posicion(x, y), velocidad, derecha));
        }
    }

    public void agregarDrone(Drone drone) {
        if (drones.size() < TOTAL_DRONES) {
            drones.add(drone);
        }
    }

    public void activarProximoDrone() {
        if (dronesActivos.size() < MAX_ACTIVOS && indiceProximo < drones.size()) {
            Drone proximo = drones.get(indiceProximo);
            proximo.activar();
            dronesActivos.add(proximo);
            indiceProximo++;
        }
    }

    public void procesarMovimiento() {
        for (int i = dronesActivos.size() - 1; i >= 0; i--) {
            Drone drone = dronesActivos.get(i);
            drone.mover();
            if (drone.completoRecorrido()) {
                drone.desactivar();
                dronesActivos.remove(i);
            }
        }
    }

    public List<Misil> procesarLanzamientos(double frecuencia, double velocidadMisil) {
        List<Misil> misils = new ArrayList<>();
        for (Drone drone : dronesActivos) {
            if (drone.puedeLanzar(frecuencia)) {
                Misil misil = drone.lanzarMisil(velocidadMisil);
                if (misil != null) {
                    misils.add(misil);
                }
            }
        }
        return misils;
    }

    // Destruye un dron impactado por un misil del jugador
    public void destruir(Drone drone) {
        drone.desactivar();
        dronesActivos.remove(drone);
    }

    public boolean estaCompleto() {
        return indiceProximo >= drones.size() && dronesActivos.isEmpty();
    }

    public List<Drone> getDronesActivos() { return dronesActivos; }
    public int cantidadActivos() { return dronesActivos.size(); }
}
