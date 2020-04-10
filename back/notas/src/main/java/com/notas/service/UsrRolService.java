/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.service;


import com.notas.dto.UsrRolDTO;
import java.util.List;

/**
 *
 * @author jhon
 */
public interface UsrRolService {
    
    UsrRolDTO guardarRol(UsrRolDTO rolDto);
    
    UsrRolDTO eliminarRol(Integer id);
    
    UsrRolDTO consultarRol(Integer id);
    
    List<UsrRolDTO> listarRoles();
    
}
