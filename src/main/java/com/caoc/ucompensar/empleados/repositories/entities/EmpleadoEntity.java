package com.caoc.ucompensar.empleados.repositories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoEntity {
    @Id
    private String codigo;

    private String nombre;

    private boolean tipoEmpleado;

    private Integer tiempoEnInstitucion;
}
