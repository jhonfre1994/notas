/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;

import com.notas.dto.UsrRolDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.dto.UsrUsuarioRolDTO;
import java.util.List;

/**
 *
 * @author jhon
 */
public interface UsrUsuarioRolService {

    UsrUsuarioRolDTO consolsultar(Integer id);

    UsrUsuarioRolDTO guardarUsrUsuRol(UsrUsuarioRolDTO dto);

    UsrUsuarioRolDTO eliminarUsrUsuRol(Integer idUser, Integer idRol);

    List<UsrUsuarioRolDTO> listarRolesUsu(Integer id);

    List<UsrRolDTO> actualizarRoles(List<UsrRolDTO> roles, UsrUsuarioDTO usuario);

}
