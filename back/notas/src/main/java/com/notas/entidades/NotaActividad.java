/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Entity
@Table(name = "nota_actividad")
public class NotaActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nota")
    private Integer idNota;
    @Column(name = "nota")
    private float nota;
    @Column(name = "fecha_digitacion")
    @Temporal(TemporalType.DATE)
    private Date fechaDigitacion;
    @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad")
    @ManyToOne
    private Actividad idActividad;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_usuario")
    @ManyToOne
    private UsrUsuario idEstudiante;

}
