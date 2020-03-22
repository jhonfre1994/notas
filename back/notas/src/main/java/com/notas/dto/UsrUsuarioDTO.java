/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.dto;

import java.util.List;
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
public class UsrUsuarioDTO {

    private Integer idUsuario;
    private String nombreUsuario;
    private String nombres;
    private String apellidos;
    private String correo;
    private String genero;
    private String contrasena;
    private String rol;
//    private List<CursoDTO> cursoList;
//    private List<NotaActividadDTO> notaActividadList;
//    private List<CursoEstudianteDTO> cursoEstudianteList;
}
