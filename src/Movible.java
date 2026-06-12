// Contrato de comportamiento: una entidad que se desplaza en una direccion.
// Cada clase implementa mover() honrando solo las direcciones que le aplican:
// el misil solo ABAJO, el dron IZQUIERDA/DERECHA, el avion todas.
public interface Movible {
    void mover(Direccion direccion);
}
