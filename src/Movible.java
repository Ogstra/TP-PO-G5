// Contrato de comportamiento para toda entidad que se desplaza por si misma
// cada tick del juego (drones y misiles). El avion no lo implementa porque
// se mueve por input del jugador, con otra firma.
public interface Movible {
    void mover();
}
