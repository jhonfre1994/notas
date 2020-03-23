/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;

import com.notas.dto.NotaActividadDTO;
import java.util.List;

/**
 *
 * @author UTP
 */
public interface NotaActividadService {
    
    List<NotaActividadDTO> listarNotas(Integer idActividad);
    
    NotaActividadDTO actualizarNota(NotaActividadDTO nota);
    
}
