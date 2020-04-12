/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.UsrRolDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.dto.UsuarioSaveDTO;
import com.notas.dto.login;
import com.notas.entidades.UsrRol;
import com.notas.entidades.UsrUsuario;
import com.notas.entidades.UsrUsuarioRol;
import com.notas.exceptions.responses.NoContentException;
import com.notas.exceptions.responses.NotFoundException;
import com.notas.repositorios.UsrUsuarioRepository;
import com.notas.repositorios.UsrUsuarioRolRepository;
import com.notas.service.UsrUsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author UTP
 */
@Service
public class UsrUsuarioServiceimpl implements UsrUsuarioService {

    @Autowired
    private UsrUsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsrUsuarioRolImlp usrUsuarioRolImlp;

    @Autowired
    private UsrRolImpl usrRolImpl;

    @Autowired
    private UsrUsuarioRolRepository usrUsuarioRolRepository;


    @Override
    public UsuarioSaveDTO guardarUsuario(UsuarioSaveDTO usuario) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        UsrUsuario usuAux;
        UsrUsuarioDTO usuExist = consultarNombreUsuario(usuario.getUsuario().getNombreUsuario());
        UsuarioSaveDTO res = new UsuarioSaveDTO();
        try {
            if (usuExist != null) {
                if (usuario.getUsuario().getContrasena().equals("")) {
                    usuario.getUsuario().setContrasena(usuExist.getContrasena());
                } else {
                    usuario.getUsuario().setContrasena(new BCryptPasswordEncoder().encode(usuario.getUsuario().getContrasena()));
                }
            } else {
                usuario.getUsuario().setContrasena(new BCryptPasswordEncoder().encode(usuario.getUsuario().getContrasena()));
            }

            usuAux = usuarioRepository.save(mapper.map(usuario.getUsuario(), UsrUsuario.class));
            if (!usuario.getRoles().isEmpty()) {
                List<UsrRolDTO> roles = usrUsuarioRolImlp.actualizarRoles(usuario.getRoles(), mapper.map(usuAux, UsrUsuarioDTO.class));
                res.setRoles(roles);
            }
            res.setUsuario(mapper.map(usuAux, UsrUsuarioDTO.class));
            return res;
        } catch (Exception ex) {
            Logger.getLogger(UsrUsuarioServiceimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UsrUsuarioDTO consultarUsuario(Integer id) {
        UsrUsuarioDTO res;
        Optional<UsrUsuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            res = mapper.map(usuario.get(), UsrUsuarioDTO.class);
            res.setRoles(usrUsuarioRolImlp.rolesUsuario(res));
            return res;
        }
        return null;
    }

    @Override
    public UsrUsuarioDTO consultarNombreUsuario(String usuario) {
        UsrUsuarioDTO res;
        UsrUsuario usu = usuarioRepository.findByNombreUsuario(usuario);
        if (usu != null) {
            res = mapper.map(usu, UsrUsuarioDTO.class);
            res.setRoles(usrUsuarioRolImlp.rolesUsuario(res));
            return res;
        }
        return null;
    }

    @Override
    public UsrUsuarioDTO eliminarUsuario(Integer id) {
        UsrUsuarioDTO usu = consultarUsuario(id);
        if (usu != null) {
            usuarioRepository.deleteById(id);
            return usu;
        }
        return null;
    }

    @Override
    public UsrUsuarioDTO iniciarSesion(login login) {
//
//        UsrUsuarioDTO usu = consultarNombreUsuario(login.getUsername());
//
//        if (usu != null) {
//            try {
//                String pass = "";
//                if (pass.equals(login.getPassword())) {
//                    return mapper.map(usu, UsrUsuarioDTO.class);
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(UsrUsuarioServiceimpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
        throw new NotFoundException("Usuario y/o contraseña incorrectos");
    }

    @Override
    public List<UsrUsuarioDTO> listarTodos() {
        List<UsrUsuario> usus = usuarioRepository.findAll();
        List<UsrUsuarioDTO> res = new ArrayList<>();
        if (!usus.isEmpty()) {
            for (UsrUsuario usu : usus) {
                UsrUsuarioDTO item;
                item = mapper.map(usu, UsrUsuarioDTO.class);
                item.setNombreCompleto(item.getNombres() + " " + item.getApellidos());
                item.setRoles(usrUsuarioRolImlp.rolesUsuario(item));
                res.add(item);
            }
            return res;
        }
        throw new NoContentException("No existen usuarios en la base de datos");
    }

    @Override
    public List<UsrUsuarioDTO> buscarUsuarosPorRol(String role) {
        List<UsrUsuarioDTO> res = new ArrayList<>();
        UsrRolDTO rol = usrRolImpl.consultarRolname(role);
        if (rol != null) {
            List<UsrUsuarioRol> rolesUsr = usrUsuarioRolRepository.findByIdRol(mapper.map(rol, UsrRol.class));
            if (rolesUsr != null) {
                for (UsrUsuarioRol ite : rolesUsr) {
                    UsrUsuarioDTO item;
                    item = mapper.map(ite.getUserId(), UsrUsuarioDTO.class);
                    item.setNombreCompleto(item.getNombres() + " " + item.getApellidos());
                    res.add(item);
                }
                return res;
            }
        }
        throw new NoContentException("No existen profesores en la base de datos, por favor cree uno");
    }


}
