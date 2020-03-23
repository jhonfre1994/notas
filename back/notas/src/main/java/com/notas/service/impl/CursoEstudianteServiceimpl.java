/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.CursoEstudianteDTO;
import com.notas.dto.SaveCursoEstudianteDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.entidades.Curso;
import com.notas.entidades.CursoEstudiante;
import com.notas.entidades.UsrUsuario;
import com.notas.exceptions.responses.BadRequestException;
import com.notas.exceptions.responses.NoContentException;
import com.notas.repositorios.CursoEstudianteRepository;
import com.notas.service.CursoEstudianteService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author UTP
 */
@Service
public class CursoEstudianteServiceimpl implements CursoEstudianteService {

    @Autowired
    private CursoEstudianteRepository cursoEstudianteRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UsrUsuarioDTO> guardarCursoEstudiante(SaveCursoEstudianteDTO cursoUsr) {
        for (UsrUsuarioDTO estudiante : cursoUsr.getEstudiantes()) {
            CursoEstudianteDTO exist = consultarEstudianteCurso(cursoUsr.getCurso().getIdCurso(),
                    estudiante.getIdUsuario());
            if (exist == null) {
                CursoEstudiante res = new CursoEstudiante();
                res.setIdCurso(mapper.map(cursoUsr.getCurso(), Curso.class));
                res.setIdEstudiante(mapper.map(estudiante, UsrUsuario.class));
                res = cursoEstudianteRepository.save(res);
            }
        }
        return cursoUsr.getEstudiantes();
    }

    public CursoEstudianteDTO consultarEstudianteCurso(Integer idCurso, Integer idEstudiante) {
        CursoEstudiante res = cursoEstudianteRepository.buscarCursoAsignado(idCurso, idEstudiante);
        if (res != null) {
            return mapper.map(res, CursoEstudianteDTO.class);
        }
        return null;
    }

    @Override
    public List<UsrUsuarioDTO> listaPorCurso(Integer id) {
        List<CursoEstudiante> datos = cursoEstudianteRepository.cursosEstudiantes(id);
        List<UsrUsuarioDTO> res = new ArrayList<UsrUsuarioDTO>();

        if (!datos.isEmpty()) {
            for (CursoEstudiante dato : datos) {
                res.add(mapper.map(dato.getIdEstudiante(), UsrUsuarioDTO.class));
            }
            return res;
        }
        throw new BadRequestException("No hay estudiantes en este grupo");

    }

    @Override
    public CursoEstudianteDTO eliminarEstudianteCrusto(Integer idCurso, Integer idEstudiante) {

        CursoEstudianteDTO res = consultarEstudianteCurso(idCurso, idEstudiante);

        if (res != null) {
            cursoEstudianteRepository.deleteById(res.getId());
            return res;
        }
        return null;

    }

    @Override
    public List<CursoEstudianteDTO> listarTodos() {

        List<CursoEstudiante> res = cursoEstudianteRepository.findAll();
        List<CursoEstudianteDTO> res2 = new ArrayList<CursoEstudianteDTO>();
        if (!res.isEmpty()) {
            for (CursoEstudiante re : res) {
                res2.add(mapper.map(re, CursoEstudianteDTO.class));
            }
            return res2;
        }
        return null;
    }

}
