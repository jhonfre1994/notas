/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.UsrRolDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.dto.UsrUsuarioRolDTO;
import com.notas.entidades.UsrRol;
import com.notas.entidades.UsrUsuario;
import com.notas.entidades.UsrUsuarioRol;
import com.notas.repositorios.UsrUsuarioRolRepository;
import com.notas.service.UsrUsuarioRolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import org.modelmapper.ModelMapper;

/**
 *
 * @author jhon
 */
@Service
public class UsrUsuarioRolImlp implements UsrUsuarioRolService {

    @Autowired
    private UsrUsuarioRolRepository usrUsuarioRolRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsrUsuarioServiceimpl usrUsuariosServiceImlp;

    @Autowired
    private UsrRolImpl usrRolImpl;

    @Override
    public UsrUsuarioRolDTO consolsultar(Integer id) {
        Optional<UsrUsuarioRol> usr = usrUsuarioRolRepository.findById(id);
        if (usr.isPresent()) {
            return mapper.map(usr.get(), UsrUsuarioRolDTO.class);
        }
        return null;
    }

    @Override
    public UsrUsuarioRolDTO guardarUsrUsuRol(UsrUsuarioRolDTO dto) {
        UsrUsuarioRol usrEntity = new UsrUsuarioRol();
        UsrUsuarioRol res;

        UsrUsuarioDTO usuario;
        UsrRolDTO rol;

        if (dto.getIdRol() == null && dto.getUserId() == null) {
            return null;
        } else {
            usuario = usrUsuariosServiceImlp.consultarNombreUsuario(dto.getUserId().getNombreUsuario());
            rol = usrRolImpl.consultarRol(dto.getIdRol().getIdRol());
            usrEntity.setUserId(mapper.map(usuario, UsrUsuario.class));
            usrEntity.setIdRol(mapper.map(rol, UsrRol.class));
            res = usrUsuarioRolRepository.save(mapper.map(dto, UsrUsuarioRol.class));
        }
        return mapper.map(res, UsrUsuarioRolDTO.class);
    }

    @Override
    public UsrUsuarioRolDTO eliminarUsrUsuRol(Integer idUser, Integer idRol) {
        List<UsrUsuarioRol> rolAsignado = usrUsuarioRolRepository.buscarRolAsignado(idUser, idRol);
        UsrUsuarioRolDTO res = consolsultar(rolAsignado.get(0).getId());
        if (res != null) {
            usrUsuarioRolRepository.deleteInBatch(rolAsignado);
        }
        return mapper.map(rolAsignado, UsrUsuarioRolDTO.class);
    }

    @Override
    public List<UsrUsuarioRolDTO> listarRolesUsu(Integer id) {
        List<UsrUsuarioRol> lista = usrUsuarioRolRepository.listarRoles(id);
        List<UsrUsuarioRolDTO> respuesta = new ArrayList<>();

        if (lista != null && !lista.isEmpty()) {
            for (UsrUsuarioRol usu : lista) {
                respuesta.add(mapper.map(usu, UsrUsuarioRolDTO.class));
            }
        }
        return respuesta;
    }

    public List<UsrRolDTO> rolesUsuario(UsrUsuarioDTO usuario) {
        List<UsrUsuarioRol> lista = usrUsuarioRolRepository.listarRoles(usuario.getIdUsuario());
        if (!lista.isEmpty()) {
            List<UsrRolDTO> res = new ArrayList<>();
            for (UsrUsuarioRol rol : lista) {
                res.add(mapper.map(rol.getIdRol(), UsrRolDTO.class));
            }
            return res;
        }
        return new ArrayList<>();
    }

    public static <T> boolean listEqualsIgnoreOrder(List<UsrRolDTO> list1, List<UsrRolDTO> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    @Override
    public List<UsrRolDTO> actualizarRoles(List<UsrRolDTO> roles, UsrUsuarioDTO usuario) {
        List<UsrUsuarioRol> lista = usrUsuarioRolRepository.listarRoles(usuario.getIdUsuario());
        List<UsrUsuarioRolDTO> respuesta = new ArrayList<>();
        List<UsrRolDTO> rolesUsu = rolesUsuario(usuario);
        System.out.println(listEqualsIgnoreOrder(rolesUsu, roles));
        if (listEqualsIgnoreOrder(rolesUsu, roles) == false) {
            if (lista != null || !lista.isEmpty()) {
                usrUsuarioRolRepository.deleteInBatch(lista);
                for (UsrRolDTO rol : roles) {
                    UsrUsuarioRol usrEntity = new UsrUsuarioRol();
                    usrEntity.setId(0);
                    usrEntity.setUserId(mapper.map(usuario, UsrUsuario.class));
                    usrEntity.setIdRol(mapper.map(rol, UsrRol.class));
                    usrUsuarioRolRepository.save(usrEntity);
//                respuesta.add(mapper.map(usrEntity, UsrUsuarioRolDTO.class));
                }
            }
        }
        return roles;
    }

}
