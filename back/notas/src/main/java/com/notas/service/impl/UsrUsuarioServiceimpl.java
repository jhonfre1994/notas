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
import com.notas.utils.PasswordUtils;
import com.notas.web.UsuariosController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private PasswordUtils PasswordUtils;

    @Override
    public UsrUsuarioDTO guardarUsuario(UsrUsuarioDTO usuario) {
        UsrUsuario res;
        try {
            usuario.setContrasena(PasswordUtils.encrypt(usuario.getContrasena(), "Adw24daAFT42dA346SV"));
            res = usuarioRepository.save(mapper.map(usuario, UsrUsuario.class));
            return mapper.map(res, UsrUsuarioDTO.class);
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

        UsrUsuarioDTO usu = consultarNombreUsuario(login.getUsername());

        if (usu != null) {
            try {
                String pass = "";
                pass = PasswordUtils.decrypt(usu.getContrasena(), "Adw24daAFT42dA346SV");
                if (pass.equals(login.getPassword())) {
                    return mapper.map(usu, UsrUsuarioDTO.class);
                }
            } catch (Exception ex) {
                Logger.getLogger(UsrUsuarioServiceimpl.class.getName()).log(Level.SEVERE, null, ex);
            }

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

    @Override
    public List<UsrUsuarioDTO> listarEstudiantes() {

        List<UsrUsuario> usus = usuarioRepository.findAll();
        List<UsrUsuarioDTO> res = new ArrayList<>();
        if (!usus.isEmpty()) {
            for (UsrUsuario usu : usus) {
                if (usu.getRol().equals("Estudiante")) {
                    UsrUsuarioDTO item;
                    item = mapper.map(usu, UsrUsuarioDTO.class);
                    item.setNombreCompleto(item.getNombres() + " " + item.getApellidos());
                    res.add(item);
                }
            }
            return res;
        }
        throw new NoContentException("No existen estudiantes en la base de datos");
    }

    @Override
    public List<UsrUsuarioDTO> listarProfesores() {
        List<UsrUsuario> usus = usuarioRepository.findAll();
        List<UsrUsuarioDTO> res = new ArrayList<>();
        if (!usus.isEmpty()) {
            for (UsrUsuario usu : usus) {
                if (usu.getRol().equals("Profesor")) {
                    UsrUsuarioDTO item;
                    item = mapper.map(usu, UsrUsuarioDTO.class);
                    item.setNombreCompleto(item.getNombres() + " " + item.getApellidos());
                    res.add(item);
                }
            }
            return res;
        }
        throw new NoContentException("No existen profesores en la base de datos, por favor cree uno");
    }

}
