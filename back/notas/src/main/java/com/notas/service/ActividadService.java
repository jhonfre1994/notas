/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;

import com.notas.dto.ActividadDTO;
import java.util.List;

/**
 *
 * @author UTP
 */
public interface ActividadService {

    ActividadDTO consultarActividad(Integer id);

    ActividadDTO crearActividad(ActividadDTO actividad);

    ActividadDTO eliminarActividad(Integer id);

    List<ActividadDTO> actividadesCurso(Integer idCurso);

}
