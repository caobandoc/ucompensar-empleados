package com.caoc.ucompensar.empleados.repositories;

import com.caoc.ucompensar.empleados.repositories.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDao extends JpaRepository<EmpleadoEntity,String> {
}
