/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.NotaActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author UTP
 */
@Repository
public interface NotaActividadRepository extends JpaRepository<NotaActividad, Integer>{
    
}
