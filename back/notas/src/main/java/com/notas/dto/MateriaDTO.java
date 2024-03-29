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
 * @author UTP
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {
    
    private Integer idMateria;
    private String nombre;
    private String abreviatura;
    private UsrUsuarioDTO profesor;
    private CursoDTO idCurso;
    private String responsable; 
}
