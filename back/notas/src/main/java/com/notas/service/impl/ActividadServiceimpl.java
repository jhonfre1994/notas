/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.ActividadDTO;
import com.notas.entidades.Actividad;
import com.notas.exceptions.responses.BadRequestException;
import com.notas.exceptions.responses.NotFoundException;
import com.notas.repositorios.ActividadRepository;
import com.notas.service.ActividadService;
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
public class ActividadServiceimpl implements ActividadService{
    
    @Autowired
    private ActividadRepository actividadRepository;
    
    @Autowired
    private ModelMapper mapper;

    @Override
    public ActividadDTO consultarActividad(Integer id) {
        Optional<Actividad> acti = actividadRepository.findById(id);
        
        if(acti.isPresent()){
            return mapper.map(acti.get(), ActividadDTO.class);
        }
        throw new NotFoundException("No se encontro la actividad");
    }

    @Override
    public ActividadDTO crearActividad(ActividadDTO actividad) {
        Actividad res;
        if(actividad.getIdMateria()== null || actividad.getIdMateria().getIdMateria() == null){
            throw new BadRequestException("Debe seleccionar una materia");
        }
        res = actividadRepository.save(mapper.map(actividad, Actividad.class));
        
        return mapper.map(res,ActividadDTO.class); 
    }

    @Override
    public ActividadDTO eliminarActividad(Integer id) {
        ActividadDTO act = consultarActividad(id);
        
        if(act != null){
            actividadRepository.deleteById(id);
            return act;
        }
        throw new BadRequestException("No se pudo eliminar la actividad");
    }

    @Override
    public List<ActividadDTO> actividadesPorMateria(Integer idMateria) {
        List<Actividad> actividades = actividadRepository.actividadesPorMateria(idMateria);
        List<ActividadDTO> res = new ArrayList<>();
        if(!actividades.isEmpty()){
            for (Actividad act : actividades) {
                res.add(mapper.map(act, ActividadDTO.class));
            }
            return res;
        }
        
        throw new BadRequestException("No hay actividades relacionadas con este curso");
        
        
    }
}
