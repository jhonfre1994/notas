/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.Actividad;
import com.notas.entidades.NotaActividad;
import com.notas.entidades.UsrUsuario;
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
public interface NotaActividadRepository extends JpaRepository<NotaActividad, Integer> {

    List<NotaActividad> findByIdActividad(Actividad actividad);

    NotaActividad findByIdEstudianteAndIdActividad(UsrUsuario estudiante, Actividad actividad);

    @Query(value = "select *\n"
            + "from nota_actividad na\n"
            + "where na.id_actividad = :idAct and id_estudiante = :idEs", nativeQuery = true)
    NotaActividad notaActividadEstudiante(@Param("idAct") Integer idAct, @Param("idEs") Integer idEs);

    List<NotaActividad> findByIdEstudiante(UsrUsuario estudiante);
}
