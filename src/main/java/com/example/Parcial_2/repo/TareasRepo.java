package com.example.Parcial_2.repo;

import com.example.Parcial_2.domain.entities.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepo extends JpaRepository<Tareas, Long> {
}