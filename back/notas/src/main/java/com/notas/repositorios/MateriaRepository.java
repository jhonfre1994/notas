/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.Curso;
import com.notas.entidades.Materia;
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
public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    @Query(value = "select *\n"
            + "from materia\n"
            + "where id_curso = :id", nativeQuery = true)
    List<Materia> materiasCurso(@Param("id") Integer id);

}
