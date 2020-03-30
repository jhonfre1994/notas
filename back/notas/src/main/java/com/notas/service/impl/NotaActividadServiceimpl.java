/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.ActividadDTO;
import com.notas.dto.CursoDTO;
import com.notas.dto.CursoEstudianteDTO;
import com.notas.dto.NotaActividadDTO;
import com.notas.dto.NotasEstudianteDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.entidades.Actividad;
import com.notas.entidades.CursoEstudiante;
import com.notas.entidades.NotaActividad;
import com.notas.entidades.UsrUsuario;
import com.notas.exceptions.responses.BadRequestException;
import com.notas.repositorios.CursoEstudianteRepository;
import com.notas.repositorios.NotaActividadRepository;
import com.notas.service.NotaActividadService;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.decimal4j.util.DoubleRounder;
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

    @Autowired
    private CursoEstudianteRepository cursoEstudianteRepository;

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
    public List<NotaActividadDTO> reporteNotas(Integer estudiante, Integer curso) {
        List<NotaActividadDTO> notasRes = new ArrayList<>();
        UsrUsuarioDTO usu = usuarioServiceimpl.consultarUsuario(estudiante);

        if (usu != null) {

            List<ActividadDTO> act = actividadServiceimpl.actividadesCurso(curso);

            for (ActividadDTO a : act) {
                NotaActividad nota = notaActividadRepository.findByIdEstudianteAndIdActividad(
                        mapper.map(usu, UsrUsuario.class),
                        mapper.map(a, Actividad.class));

                if (nota != null) {
                    notasRes.add(mapper.map(nota, NotaActividadDTO.class));
                }
            }
            return notasRes;
        } else {
            throw new BadRequestException("No hay notas registradas para este estudiante");
        }
    }

    public List<CursoDTO> cursosEstudiantes(UsrUsuarioDTO usuario) {
        List<CursoEstudiante> res = cursoEstudianteRepository.findByIdEstudiante(
                mapper.map(usuario, UsrUsuario.class));
        List<CursoDTO> res2 = new ArrayList<CursoDTO>();

        if (res != null) {
            for (CursoEstudiante re : res) {
                res2.add(mapper.map(re.getIdCurso(), CursoDTO.class));
            }
            return res2;
        }
        throw new BadRequestException("El estudiante no tiene cursos asignados");

    }

    @Override
    public List<NotasEstudianteDTO> notasPorEstudiante(Integer estudiante) {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        UsrUsuarioDTO usu = usuarioServiceimpl.consultarUsuario(estudiante);
        List<NotasEstudianteDTO> res = new ArrayList<NotasEstudianteDTO>();
        if (usu != null) {
            List<CursoDTO> cursos = cursosEstudiantes(usu);

            if (!cursos.isEmpty()) {
                for (CursoDTO curso : cursos) {
                    NotasEstudianteDTO item = new NotasEstudianteDTO();
                    List<NotaActividadDTO> notas = reporteNotas(usu.getIdUsuario(), curso.getIdCurso());
                    if (!notas.isEmpty()) {
                        item.setNotas(notas);
                        item.setPromedio(DoubleRounder.round(calcularPromedio(notas), 2));

                    }
                    item.setCurso(curso);
                    res.add(item);
                }
                return res;
            } else {
                throw new BadRequestException("El estudiante no tiene cursos asignados");
            }
        } else {
            throw new BadRequestException("No se encontro el estudiante");
        }
    }

    public Double calcularPromedio(List<NotaActividadDTO> notas) {
        double res = 0.0;
        for (NotaActividadDTO nota : notas) {
            res += nota.getNota();
        }

        res = res / notas.size();

        return res;
    }

}
