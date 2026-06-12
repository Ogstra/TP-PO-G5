// Contrato para toda entidad que puede recibir dano y reaccionar a el.
// Lo implementan el Jugador (pierde energia) y el Drone (es destruido).
public interface Danable {
    void recibirDanio(double valor);
}
