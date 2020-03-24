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
import com.notas.entidades.UsrUsuario;
import com.notas.exceptions.responses.BadRequestException;
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

    @Autowired
    private UsrUsuarioServiceimpl usuarioServiceimpl;

    @Override
    public List<NotaActividadDTO> listarNotas(Integer idActividad) {
        ActividadDTO actividad = actividadServiceimpl.consultarActividad(idActividad);
        List<NotaActividadDTO> notasRes = new ArrayList<>();
        if (actividad != null) {
            List<UsrUsuarioDTO> estudiantes = cursoEstudianteServiceimpl.listaPorCurso(actividad.getIdCurso().getIdCurso());

            if (!estudiantes.isEmpty()) {
                for (UsrUsuarioDTO estudiante : estudiantes) {
                    NotaActividad item = new NotaActividad();
                    item = notaActividadRepository.findByIdEstudianteAndIdActividad(
                            mapper.map(estudiante, UsrUsuario.class),
                            mapper.map(actividad, Actividad.class));

                    if (item != null) {
                        notasRes.add(mapper.map(item, NotaActividadDTO.class));
                    } else {
                        NotaActividadDTO temp = new NotaActividadDTO();
                        temp = guardarNotas(estudiante, actividad);
                        notasRes.add(temp);
                    }
                }
            } else {
                throw new BadRequestException("No hay estudiantes en este curso");
            }
        }
        return notasRes;
    }

    public NotaActividadDTO guardarNotas(UsrUsuarioDTO estudiante, ActividadDTO actividad) {
        List<NotaActividadDTO> notasRes = new ArrayList<>();
        NotaActividad resAux;
        NotaActividadDTO item = new NotaActividadDTO();
        item.setIdNota(0);
        item.setFechaDigitacion(new Date());
        item.setIdActividad(actividad);
        item.setIdEstudiante(estudiante);
        item.setNota((float) 0.0);
        resAux = notaActividadRepository.save(mapper.map(item, NotaActividad.class));
        return mapper.map(resAux, NotaActividadDTO.class);
    }

    @Override
    public NotaActividadDTO actualizarNota(NotaActividadDTO nota) {
        NotaActividad res;
        res = notaActividadRepository.save(mapper.map(nota, NotaActividad.class));

        return mapper.map(res, NotaActividadDTO.class);

    }

    @Override
    public List<NotaActividadDTO> reporteNotas(Integer estudiante) {
        List<NotaActividadDTO> notasRes = new ArrayList<>();

        UsrUsuarioDTO usu = usuarioServiceimpl.consultarUsuario(estudiante);

        if (usu != null) {
            List<NotaActividad> notas = notaActividadRepository.findByIdEstudiante(
                    mapper.map(usu, UsrUsuario.class));

            if (!notas.isEmpty()) {
                for (NotaActividad nota : notas) {
                    notasRes.add(mapper.map(nota, NotaActividadDTO.class));
                }
                return notasRes;
            }
        } else {
            throw new BadRequestException("No hay notas registradas para este estudiante");
        }
        throw new BadRequestException("No se encontro estudiante");
    }
}
