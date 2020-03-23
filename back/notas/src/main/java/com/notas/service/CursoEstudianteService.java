/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;

import com.notas.dto.CursoEstudianteDTO;
import com.notas.dto.SaveCursoEstudianteDTO;
import com.notas.dto.UsrUsuarioDTO;
import java.util.List;

/**
 *
 * @author UTP
 */
public interface CursoEstudianteService {

    List<UsrUsuarioDTO> guardarCursoEstudiante(SaveCursoEstudianteDTO cursoUsr);

    List<UsrUsuarioDTO> listaPorCurso(Integer id);
    
    CursoEstudianteDTO eliminarEstudianteCrusto(Integer idCurso, Integer idEstudiante);
    
    List<CursoEstudianteDTO> listarTodos();
}
