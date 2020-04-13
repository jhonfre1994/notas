/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;

import com.notas.dto.MateriaDTO;
import java.util.List;

/**
 *
 * @author UTP
 */
public interface MateriaService {
    MateriaDTO buscarMateria(Integer id);
    
    MateriaDTO guardarMateria(MateriaDTO materia);
    
    MateriaDTO eliminarMateria(Integer id);
    
    List<MateriaDTO> listarMaterias();
}
