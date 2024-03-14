package com.caoc.ucompensar.empleados.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Empleado {
    @NotBlank(message = "El código del empleado es obligatorio")
    @Pattern(regexp = "^\\d{3}$", message = "El código del empleado debe tener 3 dígitos, no puede ser 000")
    @Size(min = 3, message = "El código del empleado debe tener 3 dígitos")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre debe tener máximo 30 caracteres")
    private String nombre;

    @NotNull(message = "El tipo de empleado es obligatorio")
    private boolean tipoEmpleado;

    @Min(value = 1, message = "El tiempo en la institución debe ser de al menos 1 mes")
    @Max(value = 600, message = "El tiempo en la institución no puede ser mayor a 600 meses")
    private Integer tiempoEnInstitucion;
}
