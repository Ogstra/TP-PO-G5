# Sky Defense — Diagrama de Clases

```mermaid
classDiagram
    direction TB

    class EntidadVoladora {
        <<abstract>>
        #String id
        #Posicion posicion
        #double velocidad
        +mover()*
        +getPosicion() Posicion
        +getVelocidad() double
        +getId() String
    }

    class IDanable {
        <<interface>>
        +recibirDanio(String tipo, double valor)
    }

    class Avion {
        -boolean activo
        +mover(double dx, double dy)
        +mover()
        +reaparecer()
        +estaActivo() boolean
    }

    class Drone {
        -boolean activo
        -boolean moviendoseADerecha
        +mover()
        +lanzarMisil(double velocidadMisil) Misil
        +completoRecorrido() boolean
        +puedeLanzar(double frecuencia) boolean
        +activar()
        +desactivar()
    }

    class Misil {
        -double yDetonacion
        -boolean detonado
        +mover()
        +debeDetonar() boolean
        +detonar() Explosion
        +estaDetonado() boolean
    }

    class Explosion {
        -Posicion epicentro
        -double radioEfecto
        -double potencia
        -int framesRestantes
        +calcularDanioADistancia(Posicion) double
        +afectaA(Posicion) boolean
        +envejecer()
        +estaViva() boolean
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
        +recibirDanio(String, double)
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
    IDanable <|.. Jugador

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

- **`EntidadVoladora`** (abstracta) — generaliza el estado y comportamiento de
  todo lo que se desplaza por el espacio aereo: `Avion`, `Drone` y `Misil`.
  Aporta `posicion`, `velocidad`, `id` y el metodo abstracto `mover()`.
- **`IDanable`** (interfaz) — contrato "puede recibir dano". Implementado por
  `Jugador`. Permite aplicar dano sin acoplar a la clase concreta.
- **`Misil`** — concreto. Lo lanza un dron, desciende en linea recta y detona a
  una altitud aleatoria. El jugador **no dispara**: solo esquiva (segun consigna).

### Principios aplicados
- **Herencia** — `EntidadVoladora` como base de avion, dron y misil.
- **Polimorfismo** — `mover()` se redefine en cada entidad.
- **SRP** — `Jugador` (puntos/vida), `Avion` (posicion), `Juego` (orquestacion) separados.
