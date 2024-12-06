package com.example.Parcial_2.controller;

import com.example.Parcial_2.domain.entities.Tareas;
import com.example.Parcial_2.services.TareasService;
import com.example.Parcial_2.services.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareasController {

    // Crear la tarea (POST)
    // Obtenerlas todas (GET)
    // Una por id (GET)
    // actualizar titulo (PUT)
    // eliminar. (DELETE)

    @Autowired
    private TareasService tareasService;

    private final RedisService redisService;

    public TareasController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/{id}/exists")
    public boolean exists(@PathVariable String id) {
        return redisService.exists("tarea:" + id);
    }

    @PostMapping
    public ResponseEntity<Tareas> crearTareas(@RequestBody Tareas tarea) {
        Tareas tareaCreada = tareasService.createTareas(tarea);
        return ResponseEntity.ok(tareaCreada);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tareas>> getAllTareas() {
        List<Tareas> tarea = tareasService.getAllTareas();
        if (tarea != null) {
            return ResponseEntity.ok(tarea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{tareaId}")
    public ResponseEntity<Tareas> getTareaById(@PathVariable Long tareaId) {
        Tareas tarea = tareasService.getTareaById(tareaId);
        if (tarea != null) {
            return ResponseEntity.ok(tarea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{tareaId}")
    public ResponseEntity<Tareas> updateNombre(@PathVariable Long tareaId, @RequestBody String tarea) {
        Tareas tareaUpdated = tareasService.updateName(tareaId, tarea);
        if (tareaUpdated != null) {
            return ResponseEntity.ok(tareaUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tareaId}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long tareaId) {
        tareasService.deleteTareaById(tareaId);
        return ResponseEntity.noContent().build();
    }
}