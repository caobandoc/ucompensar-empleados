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

    @Test
    void testCrearEmpleadoWhenCodigoIsBlank() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("")
                .nombre("Test")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(12)
                .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testCrearEmpleadoWhenCodigoIs000() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("000")
                .nombre("Test")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(12)
                .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCrearEmpleadoWhenCodigoIs9999() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("9999")
                .nombre("Test")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(12)
                .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCrearEmpleadoWhenNombreIsBlank() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("123")
                .nombre("")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(12)
                .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCrearEmpleadoWhenNombreIsMoreThan30Characters() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("123")
                .nombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(12)
                .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCrearEmpleadoWhenTiempoEnInstitucionIsLessThan1() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("123")
                .nombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(0)
                .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCrearEmpleadoWhenTiempoEnInstitucionIsMoreThan600() throws Exception {
        Empleado empleado = Empleado.builder()
                .codigo("123")
                .nombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                .tipoEmpleado(true)
                .tiempoEnInstitucion(601)
                .build();
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isBadRequest());
    }

}
