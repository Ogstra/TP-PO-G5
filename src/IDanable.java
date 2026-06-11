// Contrato para toda entidad que puede recibir dano. Permite aplicar dano de
// forma uniforme sin conocer la clase concreta (avion, jugador).
public interface IDanable {
    void recibirDanio(String tipo, double valor);
}
