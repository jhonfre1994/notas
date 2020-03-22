/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;

import com.notas.dto.CursoDTO;
import java.util.List;

/**
 *
 * @author UTP
 */
public interface CursoService {
    
    CursoDTO consultarCurso(Integer id);
    
    CursoDTO guardarCruso(CursoDTO curso);
    
    CursoDTO eliminarCrusto(Integer id);
    
    List<CursoDTO> listarCursos();
    
}
