# Sky Defense

Proyecto académico desarrollado para **Paradigma Orientado a Objetos** en UADE (Ingeniería en Informática).

**Sky Defense** es un videojuego 2D inspirado en *Space Invaders*, desarrollado en Java con una interfaz gráfica construida con Swing. El objetivo del proyecto fue aplicar conceptos de diseño orientado a objetos dentro de un sistema interactivo con movimiento, colisiones, niveles, puntajes y persistencia de resultados.

> Proyecto académico realizado en un equipo de 3 integrantes.

## Funcionalidades principales

- Movimiento del jugador en cuatro direcciones.
- Disparo de misiles contra drones enemigos.
- Drones con movimiento horizontal y lanzamiento de misiles.
- Sistema de colisiones entre avión, drones y misiles.
- Daño calculado según la distancia a las explosiones.
- Sistema de energía, vidas y vidas extra.
- Progresión de niveles con aumento de dificultad.
- Menú principal e interfaz gráfica con Java Swing.
- Ranking Top 10 con persistencia local de puntajes en JSON.

## Conceptos aplicados

- Programación Orientada a Objetos.
- Herencia y clases abstractas.
- Interfaces.
- Polimorfismo.
- Encapsulamiento.
- Composición y agregación.
- Manejo de eventos con Swing.
- Persistencia básica de datos.
- Control de versiones con Git.

Entre las abstracciones principales se encuentran `EntidadVoladora`, `Misil` y las interfaces `Movible` y `Danable`, que permiten separar responsabilidades y trabajar con distintos tipos de entidades mediante contratos comunes.

## Mi participación

Participé como **coordinador de equipo y desarrollador**.

Además de colaborar en la organización de requerimientos, seguimiento del trabajo y consultas técnicas con el docente, trabajé directamente sobre la lógica del juego en clases centrales como `Juego` y `Jugador`, incluyendo funcionalidades relacionadas con:

- procesamiento de detonaciones;
- manejo de daño y pérdida de vidas;
- sistema de vidas extra;
- integración y resolución de conflictos de código.

El desarrollo fue colaborativo y el equipo utilizó Git para versionar e integrar los cambios.

## Estructura principal

```text
src/
├── Juego.java
├── Jugador.java
├── Avion.java
├── Drone.java
├── EntidadVoladora.java
├── Misil.java
├── MisilJugador.java
├── MisilEnemigo.java
├── Explosion.java
├── Escuadron.java
├── Nivel.java
├── Posicion.java
├── Movible.java
├── Danable.java
├── ControlJugador.java
├── VentanaJuego.java
├── PanelJuego.java
├── PanelMenu.java
├── PanelPuntajes.java
└── Puntajes.java
```

El diagrama de clases actualizado se encuentra en [`diagrama-clases.md`](diagrama-clases.md).

## Controles

| Acción | Tecla |
|---|---|
| Mover | `W` `A` `S` `D` |
| Disparar | `M` |
| Salir de la partida | `P` |

## Cómo ejecutarlo

### Requisitos

- JDK 17 o superior.
- IntelliJ IDEA, Eclipse o cualquier IDE compatible con Java.

### Desde un IDE

1. Abrir el repositorio como proyecto Java.
2. Configurar un JDK compatible.
3. Ejecutar `VentanaJuego.java`.

### Desde terminal

Desde la raíz del repositorio:

```bash
mkdir -p bin
javac -d bin src/*.java
java -cp bin VentanaJuego
```

## Nota académica

Este repositorio se publica como parte de mi portfolio personal para mostrar un proyecto universitario realizado en equipo. El historial de Git conserva el proceso de desarrollo colaborativo y la autoría original de los commits.
