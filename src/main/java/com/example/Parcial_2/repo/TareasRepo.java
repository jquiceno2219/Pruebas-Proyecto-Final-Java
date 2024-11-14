package com.example.Parcial_2.repo;

import com.example.Parcial_2.domain.entities.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepo extends JpaRepository<Tareas, Long> {
}