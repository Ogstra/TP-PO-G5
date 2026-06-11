import java.util.Scanner;

public class ControlJugador {
    private Juego juego;
    private Scanner scanner;

    public ControlJugador(Juego juego) {
        this.juego = juego;
        this.scanner = new Scanner(System.in);
    }

    // Lee la tecla/comando ingresado y lo procesa
    // Retorna false si el jugador eligio salir
    public boolean procesarEntrada() {
        // TODO: leer input del scanner y llamar al metodo correspondiente
        return false;
    }

    // Mueve el avion a la izquierda (dx negativo)
    private void moverIzquierda() {
        // TODO: llamar juego.procesarMovimientoAvion con dx negativo y dy 0
    }

    // Mueve el avion a la derecha (dx positivo)
    private void moverDerecha() {
        // TODO: llamar juego.procesarMovimientoAvion con dx positivo y dy 0
    }

    // Mueve el avion hacia arriba (dy positivo)
    private void moverArriba() {
        // TODO: llamar juego.procesarMovimientoAvion con dx 0 y dy positivo
    }

    // Mueve el avion hacia abajo (dy negativo)
    private void moverAbajo() {
        // TODO: llamar juego.procesarMovimientoAvion con dx 0 y dy negativo
    }

    // Lanza un misil desde la posicion actual del avion
    private void lanzarMisil() {
        // TODO: llamar juego.procesarLanzamientoMisil()
    }

    // Muestra en pantalla las opciones disponibles al jugador
    public void mostrarOpciones() {
        // TODO: imprimir las teclas/comandos validos con su descripcion
    }
}
