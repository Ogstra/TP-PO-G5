import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private static final int TOTAL_DRONES = 10;
    private static final int MAX_ACTIVOS = 4;

    private List<Drone> drones = new ArrayList<>();
    private List<Drone> dronesActivos = new ArrayList<>();
    private int indiceProximo = 0;

    // Agrega un drone al escuadron (maximo 10)
    public void agregarDrone(Drone drone) {
        // TODO: agregar drone a la lista si hay lugar
    }

    // Lanza el proximo drone si hay menos de MAX_ACTIVOS activos
    public void activarProximoDrone() {
        // TODO: sacar siguiente drone de la lista y agregarlo a dronesActivos si hay lugar
    }

    // Mueve todos los drones activos
    public void procesarMovimiento() {
        // TODO: llamar mover() en cada drone activo, desactivar los que completaron recorrido
    }

    // Cada drone activo intenta lanzar un misil segun frecuencia de disparo
    public List<Misil> procesarLanzamientos(double frecuencia) {
        // TODO: iterar drones activos, con probabilidad `frecuencia` cada uno lanza un misil
        return new ArrayList<>();
    }

    // Retorna true si todos los drones del escuadron completaron su recorrido
    public boolean estaCompleto() {
        // TODO: retornar true si indiceProximo >= TOTAL_DRONES y dronesActivos esta vacio
        return false;
    }

    public List<Drone> getDronesActivos() { return dronesActivos; }
    public int cantidadActivos() { return dronesActivos.size(); }
}
