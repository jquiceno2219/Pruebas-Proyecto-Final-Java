package com.example.Parcial_2;

import com.example.Parcial_2.controller.TareasController;
import com.example.Parcial_2.domain.entities.Tareas;
import com.example.Parcial_2.services.TareasService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class SystemEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TareasService tareasService;

    @InjectMocks
    private TareasController tareasController;

    // Endpoint: GET /tareas/{id}
    @Test
    void getTareaById() throws Exception {
        Tareas tarea = new Tareas();
        tarea.setTareaId(1L);
        tarea.setTareaTitulo("Tarea de prueba");

        when(tareasService.getTareaById(1L)).thenReturn(tarea);  // Mockea correctamente la tarea

        mockMvc.perform(get("/tareas/{tareaId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Asegúrate de que se espera un 200
                .andExpect(content().json("{\"tareaId\":1,\"tareaTitulo\":\"Tarea de prueba\"}"));
    }

    // Endpoint: DELETE /tareas/{id}
    @Test
    void deleteTarea() throws Exception {
        // No necesitamos mockear nada para métodos void. Simplemente realizamos la llamada.
        // El método delete no devuelve nada, por lo que no configuramos retorno en el when.
        doNothing().when(tareasService).deleteTareaById(1L);

        mockMvc.perform(delete("/tareas/{tareaId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()); // Esperamos un 204 No Content
    }



    // Endpoint: POST /tareas
    @Test
    void createTarea() throws Exception {
        Tareas tarea = new Tareas();
        tarea.setTareaTitulo("Nueva Tarea");

        when(tareasService.createTareas(any(Tareas.class))).thenReturn(tarea);

        mockMvc.perform(post("/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Nueva Tarea\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"nombre\":\"Nueva Tarea\"}"));
    }

    // Endpoint: PUT /tareas/{id}
    @Test
    void updateTarea() throws Exception {
        Tareas tarea = new Tareas();
        tarea.setTareaId(1L);
        tarea.setTareaTitulo("Tarea Actualizada");

        when(tareasService.updateName(1L, "Tarea Actualizada")).thenReturn(tarea);

        mockMvc.perform(put("/tareas/{tareaId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Tarea Actualizada\""))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nombre\":\"Tarea Actualizada\"}"));
    }

    // Endpoint: GET /tareas/all
    @Test
    void getAllTareas() throws Exception {
        Tareas tarea1 = new Tareas();
        tarea1.setTareaId(1L);
        tarea1.setTareaTitulo("Tarea 1");

        Tareas tarea2 = new Tareas();
        tarea2.setTareaId(2L);
        tarea2.setTareaTitulo("Tarea 2");

        when(tareasService.getAllTareas()).thenReturn(List.of(tarea1, tarea2));

        mockMvc.perform(get("/tareas/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nombre\":\"Tarea 1\"},{\"id\":2,\"nombre\":\"Tarea 2\"}]"));
    }
}
