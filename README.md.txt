# Conversor de Divisas Challenge Alura

Este proyecto es un programa en Java que permite convertir valores entre diferentes divisas en tiempo real utilizando la API de ExchangeRate-API.

## Características

- Obtiene las tasas de cambio en tiempo real desde la API de ExchangeRate-API.
- Permite convertir un valor de una divisa a otra.
- Registra un historial de consultas con marcas de tiempo.
- Permite realizar múltiples consultas.

## Requisitos

- Java 11 o superior
- Una clave de API válida de ExchangeRate-API

## Instalación

1. Clona este repositorio en tu máquina local.

    ```bash
    git clone https://github.com/tu_usuario/ConversorDeMonedaChallenge.git
    ```

2. Navega al directorio del proyecto.

    ```bash
    cd ConversorDeMonedaChallenge
    ```

3. Compila las clases Java.

    ```bash
    javac *.java
    ```

## Uso

1. Ejecuta el programa.

    ```bash
    java Cliente 
    ```

2. Sigue las instrucciones en la consola para realizar conversiones de divisas.

## Estructura del Proyecto

- `Cliente.java`: Clase principal que interactúa con el usuario.
- `Request.java`: Clase que maneja las solicitudes HTTP a la API.
- `Response.java`: Clase que maneja las respuestas de la API.
- `Historial.java`: Clase que mantiene un registro de las conversiones realizadas.

## Ejemplo de Uso

1. Ingresa la divisa base (por ejemplo, USD).
2. Ingresa la divisa a convertir (por ejemplo, EUR).
3. Ingresa el valor a convertir.
4. El programa mostrará el valor convertido y registrará la conversión en el historial.

## Historial

El historial de conversiones se muestra al final de la sesión y contiene información sobre las conversiones realizadas, incluyendo la fecha y hora.


## Autor

Este proyecto está hecho por Eduardo Aguirre en el curso de Alura.
