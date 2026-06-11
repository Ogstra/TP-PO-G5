# Sky Defense — Diagrama de Clases

```mermaid
%%{init: {'flowchart': {'curve': 'linear'}}}%%
classDiagram
    direction TB

    class EntidadVoladora {
        <<abstract>>
        #String id
        #Posicion posicion
        #double velocidad
        +mover()* void
        +getPosicion() Posicion
        +getVelocidad() double
        +getId() String
    }

    class IDanable {
        <<interface>>
        +recibirDanio(String tipo, double valor) void
    }

    class Avion {
        -boolean activo
        +mover(double dx, double dy) void
        +mover() void
        +generarMisil() Misil
        +recibirDanio(String tipo, double valor) void
        +reaparecer() void
        +estaActivo() boolean
    }

    class Drone {
        -boolean activo
        -boolean moviendoseADerecha
        +mover() void
        +lanzarMisil(double velocidadMisil) Misil
        +completoRecorrido() boolean
        +puedeLanzar(double frecuencia) boolean
        +activar() void
        +desactivar() void
    }

    class Misil {
        <<abstract>>
        #boolean detonado
        +alcanzoDetonacion()* boolean
        +esDelJugador()* boolean
        +detonar() Explosion
        +detonarPorColision() Explosion
        +estaDetonado() boolean
    }

    class MisilJugador {
        +mover() void
        +alcanzoDetonacion() boolean
        +esDelJugador() boolean
    }

    class MisilEnemigo {
        -double yDetonacion
        +mover() void
        +alcanzoDetonacion() boolean
        +esDelJugador() boolean
    }

    class Explosion {
        -Posicion epicentro
        -double radioEfecto
        -double potencia
        -int framesRestantes
        +calcularDanioADistancia(Posicion) double
        +afectaA(Posicion) boolean
        +envejecer() void
        +estaViva() boolean
    }

    class Posicion {
        -double x
        -double y
        +distanciaA(Posicion) double
        +setX(double) void
        +setY(double) void
    }

    class Escuadron {
        -List~Drone~ drones
        -List~Drone~ dronesActivos
        -int indiceProximo
        +activarProximoDrone() void
        +procesarMovimiento() void
        +procesarLanzamientos(double, double) List~Misil~
        +destruir(Drone) void
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
        +sumarPuntos(int) void
        +recibirDanio(String, double) void
        +perderVida() void
        +ganarVidaExtra() void
        +estaVivo() boolean
    }

    class Juego {
        -List~Misil~ misiles
        -List~Explosion~ explosionesRecientes
        -boolean enCurso
        -Escuadron escuadron
        -Nivel nivel
        +procesarMovimientoAvion(double, double) void
        +procesarLanzamientoMisil() void
        +procesarEscuadron() void
        +procesarCaidaMisiles() void
        +procesarColisiones() void
        +aplicarDanioSegunDistancia(double) void
        +avanzarNivel() void
        +debeContinuar() boolean
    }

    EntidadVoladora <|-- Avion
    EntidadVoladora <|-- Drone
    EntidadVoladora <|-- Misil
    Misil <|-- MisilJugador
    Misil <|-- MisilEnemigo
    IDanable <|.. Avion
    IDanable <|.. Jugador

    Avion ..> Misil : genera
    Drone ..> MisilEnemigo : lanza
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

- **`EntidadVoladora`** (abstracta) — generaliza el estado y comportamiento comun
  de todo lo que se desplaza por el espacio aereo: `Avion`, `Drone` y `Misil`.
  Aporta `posicion`, `velocidad`, `id` y el metodo abstracto `mover()`.
- **`IDanable`** (interfaz) — contrato de "puede recibir dano". Implementado por
  `Avion` y `Jugador`, permitiendo aplicar dano sin conocer la clase concreta.
- **`Misil`** (abstracta) → `MisilJugador` (asciende, solo explota por colision)
  y `MisilEnemigo` (desciende, detona a una altitud aleatoria). Polimorfismo:
  `Juego` opera sobre `Misil` sin ramas `if`.

### Principios aplicados
- **Herencia** — `EntidadVoladora` y `Misil` como bases.
- **Polimorfismo (LSP)** — subtipos de `Misil` sustituibles donde se espera `Misil`.
- **OCP** — agregar un nuevo tipo de misil o entidad = nueva subclase, sin tocar `Juego`.
