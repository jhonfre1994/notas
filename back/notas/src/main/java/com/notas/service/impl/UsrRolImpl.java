/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;


import com.notas.dto.UsrRolDTO;
import com.notas.entidades.UsrRol;
import com.notas.repositorios.UsrRolRepository;
import com.notas.service.UsrRolService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhon
 */
@Service
public class UsrRolImpl implements UsrRolService {

    @Autowired
    private UsrRolRepository usrRolRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UsrRolDTO guardarRol(UsrRolDTO rolDto) {

        UsrRol rolEntiry = new UsrRol();
        UsrRol res;

        if (rolDto.getNombre().isEmpty()) {
            return null;
        } else {
            res = usrRolRepository.save(mapper.map(rolDto, UsrRol.class));
        }
        return mapper.map(res, UsrRolDTO.class);
    }

    @Override
    public UsrRolDTO eliminarRol(Integer id) {
        UsrRolDTO rol = consultarRol(id);

        if (rol != null) {
            usrRolRepository.deleteById(id);
        }
        return rol;
    }

    @Override
    public UsrRolDTO consultarRol(Integer id) {
        Optional<UsrRol> rol = usrRolRepository.findById(id);

        if (rol.isPresent()) {
            return mapper.map(rol.get(), UsrRolDTO.class);
        }
        return null;
    }
    
    public UsrRolDTO consultarRolname(String nombreRol) {
        UsrRol rol = usrRolRepository.findByNombre(nombreRol);

        if (rol != null) {
            return mapper.map(rol, UsrRolDTO.class);
        }
        return null;
    }
    

    @Override
    public List<UsrRolDTO> listarRoles() {
        List<UsrRol> roles = usrRolRepository.findAll();
        List<UsrRolDTO> respuesta= new ArrayList<>();
        
        if(roles != null && !roles.isEmpty()){
            for (UsrRol rol : roles) {
                respuesta.add(mapper.map(rol, UsrRolDTO.class));
                
            }
        }
        return respuesta;
    }

}
