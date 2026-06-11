import java.util.Scanner;

public class ControlJugador {
    private Juego juego;
    private Scanner scanner;

    public ControlJugador(Juego juego) {
        this.juego = juego;
        this.scanner = new Scanner(System.in);
    }

    // Lee el comando ingresado y lo procesa. Retorna false si el jugador eligio salir
    public boolean procesarEntrada() {
        String comando = scanner.nextLine().trim().toUpperCase();

        switch (comando) {
            case "W": juego.procesarMovimientoAvion(0, 1); break;
            case "A": juego.procesarMovimientoAvion(-1, 0); break;
            case "S": juego.procesarMovimientoAvion(0, -1); break;
            case "D": juego.procesarMovimientoAvion(1, 0); break;
            case "M": juego.procesarLanzamientoMisil(); break;
            case "P": return false;
            default: break;
        }

        return true;
    }
}
