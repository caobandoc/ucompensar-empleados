package com.caoc.ucompensar.empleados.service;

import com.caoc.ucompensar.empleados.entities.Empleado;
import com.caoc.ucompensar.empleados.repositories.EmpleadoDao;
import com.caoc.ucompensar.empleados.repositories.entities.EmpleadoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoDao empleadoDao;

    public Empleado getEmpleado(String codigo) {
        return toMap(empleadoDao.getReferenceById(codigo));
    }

    public Empleado createEmpleado(Empleado empleado) {
        return toMap(empleadoDao.save(toMap(empleado)));
    }

    private Empleado toMap(EmpleadoEntity empleado) {
        return Empleado.builder()
                .codigo(empleado.getCodigo())
                .nombre(empleado.getNombre())
                .tipoEmpleado(empleado.isTipoEmpleado())
                .tiempoEnInstitucion(empleado.getTiempoEnInstitucion())
                .build();
    }

    private EmpleadoEntity toMap(Empleado empleado) {
        return EmpleadoEntity.builder()
                .codigo(empleado.getCodigo())
                .nombre(empleado.getNombre())
                .tipoEmpleado(empleado.isTipoEmpleado())
                .tiempoEnInstitucion(empleado.getTiempoEnInstitucion())
                .build();
    }
}
