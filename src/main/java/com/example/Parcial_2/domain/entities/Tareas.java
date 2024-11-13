package com.example.Parcial_2.domain.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tareas {
    private Long tareaId;
    private String tareaTitulo;
    private String tareaDescripcion;
}