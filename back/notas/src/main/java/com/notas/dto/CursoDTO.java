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
public class CursoDTO {
    
    private Integer idCurso;
    private String nombreCurso;
    private String codigo;
    private UsrUsuarioDTO idProfesor;
    private String responsable;
//    private List<CursoEstudianteDTO> cursoEstudianteList;
//    private List<ActividadDTO> actividadList;
}
