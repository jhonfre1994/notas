/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
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
@Table(name = "usr_usuario")
public class UsrUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 45)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Size(max = 1)
    @Column(name = "genero")
    private String genero;
    @Size(max = 100)
    @Column(name = "contrasena")
    private String contrasena;
    @Size(max = 20)
    @Column(name = "rol")
    private String rol;
    @OneToMany(mappedBy = "idEstudiante")
    private List<NotaActividad> notaActividadList;
    @OneToMany(mappedBy = "idEstudiante")
    private List<CursoEstudiante> cursoEstudianteList;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userId")
//    private List<UsrUsuarioRol> roles;

}
