/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service.impl;

import com.notas.dto.UsrUsuarioDTO;
import com.notas.dto.login;
import com.notas.entidades.UsrUsuario;
import com.notas.exceptions.responses.NoContentException;
import com.notas.exceptions.responses.NotFoundException;
import com.notas.repositorios.UsrUsuarioRepository;
import com.notas.service.UsrUsuarioService;
import com.notas.web.UsuariosController;
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
public class UsrUsuarioServiceimpl implements UsrUsuarioService {

    @Autowired
    private UsrUsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UsrUsuarioDTO guardarUsuario(UsrUsuarioDTO usuario) {
        UsrUsuario res;
        res = usuarioRepository.save(mapper.map(usuario, UsrUsuario.class));
        return mapper.map(res, UsrUsuarioDTO.class);
    }

    @Override
    public UsrUsuarioDTO consultarUsuario(Integer id) {
        UsrUsuarioDTO res;
        Optional<UsrUsuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            res = mapper.map(usuario.get(), UsrUsuarioDTO.class);
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

        UsrUsuario usu = usuarioRepository.findByNombreUsuarioAndContrasena(login.getUsername(), login.getPassword());

        if (usu != null) {
            return mapper.map(usu, UsrUsuarioDTO.class);
        }
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
                res.add(item);
            }
            return res;
        }
        throw new NoContentException("No existen usuarios en la base de datos");
    }

}
