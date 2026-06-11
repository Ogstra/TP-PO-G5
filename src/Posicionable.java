// Contrato para todo objeto del juego que ocupa una posicion en el espacio.
// Permite tratar de forma uniforme aviones, drones y misiles (calculo de
// distancias, colisiones) sin depender de la clase concreta.
public interface Posicionable {
    Posicion getPosicion();
}
