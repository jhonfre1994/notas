/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author UTP
 */
@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

    @Query(value = "select *\n"
            + "from actividad a\n"
            + "where a.id_materia = :id", nativeQuery = true)
    List<Actividad> actividadesPorMateria(@Param("id") Integer id);
}
