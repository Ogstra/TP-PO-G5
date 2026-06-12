# Sky Defense — Diagrama de Clases

```mermaid
classDiagram
    direction TB

    class EntidadVoladora {
        <<abstract>>
        #String id
        #Posicion posicion
        #double velocidad
        +getPosicion() Posicion
        +getVelocidad() double
        +getId() String
    }

    class Movible {
        <<interface>>
        +mover()
    }

    class Danable {
        <<interface>>
        +recibirDanio(double valor)
    }

    class Avion {
        +mover(double dx, double dy)
        +reaparecer()
    }

    class Drone {
        -boolean moviendoseADerecha
        -boolean vivo
        +mover()
        +recibirDanio(double valor)
        +estaVivo() boolean
        +lanzarMisil(double velocidadMisil) Misil
        +completoRecorrido() boolean
        +puedeLanzar(double frecuencia) boolean
    }

    class Misil {
        -double yDetonacion
        -boolean detonado
        +mover()
        +detonar() Explosion
        +estaDetonado() boolean
    }

    class Explosion {
        -Posicion epicentro
        -double radioEfecto
        -int framesRestantes
        +envejecer()
        +estaViva() boolean
        +getEpicentro() Posicion
    }

    class Posicion {
        -double x
        -double y
        +distanciaA(Posicion) double
        +setX(double)
        +setY(double)
    }

    class Escuadron {
        -List~Drone~ drones
        -List~Drone~ dronesActivos
        -int indiceProximo
        +activarProximoDrone()
        +procesarMovimiento()
        +procesarLanzamientos(double, double) List~Misil~
        +estaCompleto() boolean
    }

    class Nivel {
        -int numero
        -double velocidadDrones
        -double velocidadMisiles
        -double frecuenciaDisparo
        +siguiente() Nivel
        +getVelocidadDrones() double
        +getVelocidadMisiles() double
        +getFrecuenciaDisparo() double
    }

    class Jugador {
        -int puntos
        -int vida
        -int vidasRestantes
        +sumarPuntos(int)
        +recibirDanio(double valor)
        +perderVida()
        +ganarVidaExtra()
        +estaVivo() boolean
    }

    class Juego {
        -List~Misil~ misiles
        -List~Explosion~ explosionesRecientes
        -boolean enCurso
        -Escuadron escuadron
        -Nivel nivel
        +procesarMovimientoAvion(double, double)
        +procesarEscuadron()
        +procesarCaidaMisiles()
        +aplicarDanioSegunDistancia(double)
        +avanzarNivel()
        +debeContinuar() boolean
    }

    EntidadVoladora <|-- Avion
    EntidadVoladora <|-- Drone
    EntidadVoladora <|-- Misil
    Movible <|.. Drone
    Movible <|.. Misil
    Danable <|.. Drone
    Danable <|.. Jugador

    Drone ..> Misil : lanza
    Misil ..> Explosion : crea
    EntidadVoladora *-- Posicion
    Escuadron o-- Drone
    Juego *-- Jugador
    Juego *-- Avion
    Juego *-- Escuadron
    Juego *-- Nivel
    Juego o-- Misil
    Juego o-- Explosion
```

## Notas de diseño

- **`EntidadVoladora`** (clase abstracta) — comparte el **estado** comun de toda
  entidad del espacio aereo: `Avion`, `Drone`, `Misil`. Aporta `posicion`,
  `velocidad`, `id`. No se puede instanciar.
- **`Movible`** (interface) — contrato de **comportamiento** "se desplaza solo
  cada tick". Lo implementan `Drone` (cruza horizontal) y `Misil` (desciende).
  El `Avion` **no** lo implementa: se mueve por input del jugador, con otra firma.
- **`Misil`** — concreto. Lo lanza un dron, cae en linea recta y detona a una
  altitud aleatoria. El jugador no dispara: solo esquiva (segun consigna).

### Distincion clase abstracta vs interface (Clase 10)
- **Clase abstracta** (`EntidadVoladora`) — cuando se comparte **estado/codigo**
  entre clases estrechamente relacionadas.
- **Interface** (`Movible`) — cuando se especifica un **comportamiento** que
  pueden cumplir distintas clases, sin compartir implementacion.
