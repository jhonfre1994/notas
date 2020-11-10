/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.CursoDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.entidades.Curso;
import com.notas.entidades.UsrUsuario;
import com.notas.exceptions.responses.BadRequestException;
import com.notas.repositorios.CursoRepository;
import com.notas.service.CursoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author UTP
 */
@Service
public class CursoServiceimpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsrUsuarioServiceimpl usrUsuarioServiceimpl;

    @Override
    public CursoDTO consultarCurso(Integer id) {
        Optional<Curso> res = cursoRepository.findById(id);

        if (res.isPresent()) {
            return mapper.map(res.get(), CursoDTO.class);
        }
        return null;
    }

    @Override
    public CursoDTO guardarCruso(CursoDTO curso) {
        Curso res;

        if (curso.getIdProfesor() == null || curso.getIdProfesor().getIdUsuario() == null) {
            throw new BadRequestException("El curso no puede estar vacio");
        } else {
            res = cursoRepository.save(mapper.map(curso, Curso.class));
        }
        return mapper.map(res, CursoDTO.class);
    }

    @Override
    public CursoDTO eliminarCrusto(Integer id) {
        CursoDTO res = consultarCurso(id);

        if (res != null) {
            cursoRepository.deleteById(id);
            return res;
        }
        return null;

    }

    @Override
    public List<CursoDTO> listarCursos() {
        List<Curso> listCursos = cursoRepository.findAll();
        List<CursoDTO> res = new ArrayList<CursoDTO>();
        if (!listCursos.isEmpty()) {
            for (Curso listCurso : listCursos) {
                CursoDTO item;
                item = mapper.map(listCurso, CursoDTO.class);
                item.setResponsable(item.getIdProfesor().getNombres() + " " + item.getIdProfesor().getApellidos());
                res.add(item);
            }
            return res;
        }
        throw new BadRequestException("No se encuentra cursos en la base de datos, por favor cree uno");
    }

    @Override
    public List<CursoDTO> listarMisCursos(Integer idProfesor) {

        UsrUsuarioDTO usr = usrUsuarioServiceimpl.consultarUsuario(idProfesor);
        if (usr != null) {
            List<Curso> listCursos = cursoRepository.listaCusrsosPorProfesor(idProfesor);
            List<CursoDTO> res = new ArrayList<CursoDTO>();
            if (!listCursos.isEmpty()) {
                for (Curso listCurso : listCursos) {
                    CursoDTO item;
                    item = mapper.map(listCurso, CursoDTO.class);
                    item.setResponsable(item.getIdProfesor().getNombres() + " " + item.getIdProfesor().getApellidos());
                    res.add(item);
                }
                return res;
            }
        }
        throw new BadRequestException("No se encuentra en profesor en la base de datos");
    }

    @Override
    public List<CursoDTO> cursosPorJornada(String jornada) {
        List<Curso> cursos = cursoRepository.findByJornada(jornada);

        if (!cursos.isEmpty()) {
            List<CursoDTO> res = new ArrayList<>();
            for (Curso curso : cursos) {
                CursoDTO item = new CursoDTO();
                item = mapper.map(curso, CursoDTO.class);
                item.setResponsable(curso.getIdProfesor().getNombres() + " " + curso.getIdProfesor().getApellidos());
                res.add(item);
            }
            return res;
        }
        throw new BadRequestException("No hay cursos en esta jornada");
    }

}
