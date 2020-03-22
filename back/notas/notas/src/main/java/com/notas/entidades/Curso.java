/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
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
@Table(name = "curso")
@XmlRootElement
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Integer idCurso;
    @Size(max = 45)
    @Column(name = "nombre_curso")
    private String nombreCurso;
    @Size(max = 45)
    @Column(name = "codigo")
    private String codigo;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_usuario")
    @ManyToOne
    private UsrUsuario idProfesor;
    @OneToMany(mappedBy = "idCurso")
    private List<CursoEstudiante> cursoEstudianteList;
    @OneToMany(mappedBy = "idCurso")
    private List<Actividad> actividadList;

}
