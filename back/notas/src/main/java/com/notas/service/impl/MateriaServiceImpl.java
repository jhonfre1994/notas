/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.MateriaDTO;
import com.notas.entidades.Materia;
import com.notas.repositorios.MateriaRepository;
import com.notas.service.MateriaService;
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
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public MateriaDTO buscarMateria(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MateriaDTO guardarMateria(MateriaDTO materia) {
        MateriaDTO res;
        Materia enty;
        enty = materiaRepository.save(mapper.map(materia, Materia.class));
        return mapper.map(enty, MateriaDTO.class);

    }

    @Override
    public MateriaDTO eliminarMateria(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MateriaDTO> listarMaterias() {
        List<Materia> mates = materiaRepository.findAll();

        if (mates != null || !mates.isEmpty()) {
            List<MateriaDTO> res = new ArrayList<>();
            for (Materia mate : mates) {
                res.add(mapper.map(mate, MateriaDTO.class));
            }
            return res;
        }
        return null;
    }

}
