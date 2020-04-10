package com.notas.service.impl;

import com.notas.entidades.UsrUsuario;
import com.notas.entidades.UsrUsuarioRol;
import com.notas.exceptions.responses.BadRequestException;
import com.notas.repositorios.UsrUsuarioRepository;
import com.notas.repositorios.UsrUsuarioRolRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;

@Service(value = "userService")
public class AutorizacionImlp implements UserDetailsService {

    @Autowired
    private UsrUsuarioRepository usuarioRepository;

    @Autowired
    private UsrUsuarioRolRepository rolService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UsrUsuario user = usuarioRepository.findByNombreUsuario(userId);

        if (user == null) {
            throw new BadRequestException("Nombre de usuario y/o contraseña incorrectos");
        }
//        if (user.getEstado().equals("Activo")) {
        List<UsrUsuarioRol> roles = rolService.listarRoles(user.getIdUsuario());

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getIdRol().getNombre()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getNombreUsuario(), user.getContrasena(), authorities);

        return userDetails;
//        }
    }
}
