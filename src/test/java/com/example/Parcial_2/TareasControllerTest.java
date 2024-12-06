package com.example.Parcial_2;


import com.example.Parcial_2.domain.entities.Tareas;
import com.example.Parcial_2.services.TareasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TareasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareasService tareasService;  // Mock the service layer

    private Tareas tareas;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setUp() {
        tareas = new Tareas(1L, "Titulo de prueba", "Soy una descripción.");
    }

    // Test de base de datos
    @Test
    void testDatabaseConnection() throws Exception {
        assertNotNull(dataSource, "El DataSource está configurado.");
        try(Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "La conexión a la base de datos es exitosa.");
        }
    }

    @Test
    public void testCreateTarea() throws Exception {
        when(tareasService.createTareas(any(Tareas.class))).thenReturn(tareas);

        mockMvc.perform(post("/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tareaTitulo\": \"Titulo_de_prueba\", \"tareaDescripcion\": \"soy_una_descripcion.\"}"))
                .andExpect(status().isOk())  // Verify the status is OK
                .andExpect(jsonPath("$.tareaTitulo").value("Titulo de prueba"))
                .andExpect(jsonPath("$.tareaDescripcion").value("Soy una descripción."));
    }

    //Funciona.
    @Test
    public void testDeleteTarea() throws Exception {
        doNothing().when(tareasService).deleteTareaById(1L);

        mockMvc.perform(delete("/tareas/{tareaId}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetTareaById() throws Exception {
        when(tareasService.getTareaById(1L)).thenReturn(tareas);

        mockMvc.perform(get("/tareas/{tareaId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tareaTitulo").value("Titulo de prueba"))
                .andExpect(jsonPath("$.tareaDescripcion").value("Soy una descripción."));
    }

    @Test
    public void testUpdateNombre() throws Exception {
        String nuevoNombre = "Nuevo nombre de tarea";
        tareas.setTareaTitulo(nuevoNombre); // Actualizar el título en la instancia de tareas

        // Mocking the service response con la instancia actualizada
        when(tareasService.updateName(1L, nuevoNombre)).thenReturn(tareas);

        mockMvc.perform(put("/tareas/{tareaId}", 1L)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(nuevoNombre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tareaId").value(1L))
                .andExpect(jsonPath("$.tareaTitulo").value(nuevoNombre))
                .andExpect(jsonPath("$.tareaDescripcion").value("Soy una descripción."));
    }

    @Test
    public void testGetAllTareas() throws Exception {
        List<Tareas> listaTareas = Arrays.asList(
                new Tareas(1L, "Tarea 1", "Descripción 1"),
                new Tareas(2L, "Tarea 2", "Descripción 2")
        );

        when(tareasService.getAllTareas()).thenReturn(listaTareas);

        mockMvc.perform(get("/tareas/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(listaTareas.size()))
                .andExpect(jsonPath("$[0].tareaId").value(1L))
                .andExpect(jsonPath("$[0].tareaTitulo").value("Tarea 1"))
                .andExpect(jsonPath("$[0].tareaDescripcion").value("Descripción 1"))
                .andExpect(jsonPath("$[1].tareaId").value(2L))
                .andExpect(jsonPath("$[1].tareaTitulo").value("Tarea 2"))
                .andExpect(jsonPath("$[1].tareaDescripcion").value("Descripción 2"));
    }

}