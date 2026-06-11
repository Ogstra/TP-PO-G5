import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private static final int TOTAL_DRONES = 10;
    private static final int MAX_ACTIVOS = 4;

    private List<Drone> drones = new ArrayList<>();
    private List<Drone> dronesActivos = new ArrayList<>();
    private int indiceProximo = 0;

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

    public List<Misil> procesarLanzamientos(double frecuencia) {
        List<Misil> misils = new ArrayList<>();
        for (Drone drone : dronesActivos) {
            if (drone.puedeLanzar(frecuencia)) {
                Misil misil = drone.lanzarMisil();
                if (misil != null) {
                    misils.add(misil);
                }
            }
        }
        return misils;
    }

    public boolean estaCompleto() {
        return indiceProximo >= drones.size() && dronesActivos.isEmpty();
    }

    public List<Drone> getDronesActivos() { return dronesActivos; }
    public int cantidadActivos() { return dronesActivos.size(); }
}
