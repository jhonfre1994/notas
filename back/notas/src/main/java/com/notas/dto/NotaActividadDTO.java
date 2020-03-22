/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.dto;

import java.util.Date;
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
public class NotaActividadDTO {
    
    
    private Integer idNota;
    private Integer nota;
    private Date fechaDigitacion;
    private ActividadDTO idActividad;
    private UsrUsuarioDTO idEstudiante;
}
