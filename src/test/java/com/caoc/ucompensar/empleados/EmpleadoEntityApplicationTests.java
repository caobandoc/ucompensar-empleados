package com.caoc.ucompensar.empleados;

import com.caoc.ucompensar.empleados.entities.Empleado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmpleadoEntityApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearEmpleadoInBodyResponse() throws Exception {
        Empleado empleado = Empleado.builder()
                        .codigo("123")
                        .nombre("Juan")
                        .tipoEmpleado(true)
                        .tiempoEnInstitucion(12)
                        .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(empleado)));
    }

    @Test
    void testCrearEmpleadoInGetRequest() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("123")
                .nombre("Juan")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(12)
                .build();
        var responsePost = mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(empleado)));

        mockMvc.perform(get("/" + empleado.getCodigo()))
                .andExpect(status().isOk())
                .andExpect(content().json(responsePost.andReturn().getResponse().getContentAsString()));

    }

}
