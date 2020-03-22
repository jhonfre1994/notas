/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;

import com.notas.dto.UsrUsuarioDTO;
import com.notas.service.UsrUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author UTP
 */
@RestController
@RequestMapping("/api/v.1/usuarios")
public class UsuariosController {

    @Autowired
    private UsrUsuarioService usuarioService;

    public UsuariosController(UsrUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("consularPorUsuario/{nombreUsuario}")
    public ResponseEntity<?> consultaPorUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
        UsrUsuarioDTO res = usuarioService.consultarNombreUsuario(nombreUsuario);

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("consularPorId/{id}")
    public ResponseEntity<?> consultaPorUsuario(@PathVariable("id") Integer id) {
        UsrUsuarioDTO res = usuarioService.consultarUsuario(id);

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("eliminarUsuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Integer id) {
        UsrUsuarioDTO res = usuarioService.eliminarUsuario(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("guardarUsuario")
    public ResponseEntity<?> guardarUsuario(@RequestBody UsrUsuarioDTO usuario) {
        UsrUsuarioDTO res = usuarioService.guardarUsuario(usuario);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }
    
    
    
}
