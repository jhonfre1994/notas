/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.CursoEstudiante;
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
public interface CursoEstudianteRepository extends JpaRepository<CursoEstudiante, Integer> {

    @Query(value = "select ce.id, ce.id_estudiante, ce.id_curso\n"
            + "from curso_estudiante ce, curso c\n"
            + "where c.id_curso = :id and ce.id_curso = :id", nativeQuery = true)
    List<CursoEstudiante> cursosEstudiantes(@Param("id") Integer id);

    @Query(value = "select *\n"
            + "from curso_estudiante c\n"
            + "where c.id_curso = :idCruso and c.id_estudiante = :idEstudiante\n"
            + "limit 1", nativeQuery = true)
    CursoEstudiante buscarCursoAsignado(@Param("idCruso") Integer idCruso,
            @Param("idEstudiante") Integer idEstudiante);

}
