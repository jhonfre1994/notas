/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;

import com.notas.dto.UsrUsuarioDTO;

/**
 *
 * @author UTP
 */
public interface UsrUsuarioService {

    UsrUsuarioDTO guardarUsuario(UsrUsuarioDTO usuario);

    UsrUsuarioDTO consultarUsuario(Integer id);

    UsrUsuarioDTO consultarNombreUsuario(String usuario);
    
    UsrUsuarioDTO eliminarUsuario(Integer id);

}
