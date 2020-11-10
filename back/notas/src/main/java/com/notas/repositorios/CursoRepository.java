/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.Curso;
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
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    //List<Curso> findByIdProfesor(UsrUsuario usuario);

    List<Curso> findByJornada(String usuario);

    @Query(value = "WITH actividades as (SELECT id_materia, nombre, abreviatura, id_curso, profesor\n"
            + "					FROM public.materia\n"
            + "					where profesor = :idProfesor),\n"
            + "cursos as (select cu.*\n"
            + "		   from curso cu, actividades ac\n"
            + "		  where cu.id_curso = ac.id_curso\n"
            + "		  group by cu.id_curso)\n"
            + "					\n"
            + "select *\n"
            + "from cursos", nativeQuery = true)
    List<Curso> listaCusrsosPorProfesor(@Param("idProfesor") Integer idProfesor);

}
