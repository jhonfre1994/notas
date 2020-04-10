/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhon
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsrUsuarioRolDTO {
    private Integer id;
    private UsrRolDTO idRol;
    private UsrUsuarioDTO userId;
}
