package com.example.Parcial_2.services;

import com.example.Parcial_2.domain.entities.Tareas;
import com.example.Parcial_2.repo.TareasRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareasService {
    private final TareasRepo tareasRepo;

    public TareasService(TareasRepo tareasRepo) {this.tareasRepo = tareasRepo; }

    //Crear una tarea.
    public Tareas createTareas(Tareas tareas) { return tareasRepo.save(tareas); }

    // Obtener todas.
    public List<Tareas> getAllTareas(Tareas tareas) { return tareasRepo.findAll();}

    //Obtener por ID.
    public Tareas getTareaById(Long tareaId) { return tareasRepo.findById(tareaId).orElse(null) ;}

    // Actualizar.
    public Tareas updateName(Long tareaId, String name) {
        Optional<Tareas> tareas = tareasRepo.findById(tareaId);

        if (tareas.isPresent()) {
            Tareas tareaEditada = tareas.get();
            tareaEditada.setTareaTitulo(tareaEditada.getTareaTitulo());
            return tareasRepo.save(tareaEditada);
        }
        return null;
    }

    // Eliminar.
    public void deleteTareaById(Long tareaId) { tareasRepo.deleteById(tareaId); ;}

}
