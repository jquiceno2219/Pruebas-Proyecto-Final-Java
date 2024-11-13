package com.example.Parcial_2.controller;

import com.example.Parcial_2.domain.entities.Tareas;
import com.example.Parcial_2.services.TareasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
public class TareasController {

    // Crear la tarea (POST)
    // Obtenerlas todas (GET)
    // Una por id (GET)
    // actualizar titulo (PUT)
    // eliminar. (DELETE)

    private final TareasService tareasService;

    public TareasController(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    @PostMapping
    public ResponseEntity<Tareas> crearTareas(String) {

    }

}