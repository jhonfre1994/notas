/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.ActividadDTO;
import com.notas.dto.NotaActividadDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.entidades.Actividad;
import com.notas.entidades.NotaActividad;
import com.notas.repositorios.NotaActividadRepository;
import com.notas.service.NotaActividadService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author UTP
 */
@Service
public class NotaActividadServiceimpl implements NotaActividadService {

    @Autowired
    private NotaActividadRepository notaActividadRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ActividadServiceimpl actividadServiceimpl;

    @Autowired
    private CursoEstudianteServiceimpl cursoEstudianteServiceimpl;

    @Override
    public List<NotaActividadDTO> listarNotas(Integer idActividad) {
        ActividadDTO actividad = actividadServiceimpl.consultarActividad(idActividad);
        List<NotaActividadDTO> notasRes = new ArrayList<>();
        if (actividad != null) {
            List<NotaActividad> notas = notaActividadRepository.findByIdActividad(mapper.map(actividad, Actividad.class));
            if (!notas.isEmpty()) {
                for (NotaActividad nota : notas) {
                    notasRes.add(mapper.map(nota, NotaActividadDTO.class));
                }
                return notasRes;
            } else {
                List<UsrUsuarioDTO> estudiantes = cursoEstudianteServiceimpl.listaPorCurso(actividad.getIdCurso().getIdCurso());
                notasRes = guardarNotas(estudiantes, actividad);
            }
        }
        return notasRes;
    }

    public List<NotaActividadDTO> guardarNotas(List<UsrUsuarioDTO> estudiantes, ActividadDTO actividad) {
        List<NotaActividadDTO> notasRes = new ArrayList<>();
        NotaActividad resAux;
        for (UsrUsuarioDTO estudiante : estudiantes) {
            NotaActividadDTO item = new NotaActividadDTO();
            item.setIdNota(0);
            item.setFechaDigitacion(new Date());
            item.setIdActividad(actividad);
            item.setIdEstudiante(estudiante);
            item.setNota((float) 0.0);
            resAux = notaActividadRepository.save(mapper.map(item, NotaActividad.class));
            notasRes.add(mapper.map(resAux,NotaActividadDTO.class));
        }
        return notasRes;
    }

    @Override
    public NotaActividadDTO actualizarNota(NotaActividadDTO nota) {
         NotaActividad res;
        res = notaActividadRepository.save(mapper.map(nota, NotaActividad.class));
        
        return mapper.map(res,NotaActividadDTO.class);
        
    }
}
