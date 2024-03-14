package com.caoc.ucompensar.empleados.controllers;

import com.caoc.ucompensar.empleados.entities.Empleado;
import com.caoc.ucompensar.empleados.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerEmpleados() {
        return ResponseEntity.ok(empleadoService.getEmpleados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable String id) {
        return ResponseEntity.ok(empleadoService.getEmpleado(id));
    }

    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoService.createEmpleado(empleado));
    }
}
