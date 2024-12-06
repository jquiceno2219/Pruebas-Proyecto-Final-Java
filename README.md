# Pruebas-Proyecto-Final-Java

## Descripción del Proyecto

Esta API permite gestionar tareas mediante un conjunto de endpoints RESTful. Los usuarios pueden crear, leer, actualizar y eliminar tareas. Cada tarea tiene un identificador único y un título que puede ser actualizado. La API está diseñada para ofrecer una interfaz sencilla y eficiente para la gestión de tareas, y se apoya en una base de datos PostgreSQL para almacenar la información.

Los recursos de tareas se gestionan utilizando los siguientes métodos HTTP:

- **GET** `/tareas/all`: Obtiene todas las tareas.
- **GET** `/tareas/{id}`: Obtiene una tarea por su ID.
- **POST** `/tareas`: Crea una nueva tarea.
- **PUT** `/tareas/{id}`: Actualiza el título de una tarea.
- **DELETE** `/tareas/{id}`: Elimina una tarea por su ID.

Además, la aplicación está configurada para trabajar con Redis para mejorar la eficiencia en la consulta de tareas.

## Requisitos

A continuación, se presentan las herramientas necesarias para ejecutar esta API localmente:

- **Docker**: Se utiliza para contenerizar la aplicación y sus dependencias.
- **Docker Compose**: Orquesta los servicios de Docker, incluyendo la base de datos y Redis.
- **Java 11 o superior**: El lenguaje utilizado para desarrollar la API.
- **Spring Boot**: Framework basado en Java para crear aplicaciones web.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **PostgreSQL**: Base de datos utilizada para almacenar las tareas.
- **Redis**: Sistema de almacenamiento en caché utilizado para optimizar el rendimiento.

## Instrucciones de Instalación

### 1. Clonación del repositorio

Primero, clona el repositorio de la API en tu máquina local:

```bash
git clone https://github.com/jquiceno2219/Pruebas-Proyecto-Final-Java.git
````

Este proyecto utiliza Maven para gestionar las dependencias. Para instalar las dependencias necesarias, ejecuta el siguiente comando:

```bash
mvn install
```

