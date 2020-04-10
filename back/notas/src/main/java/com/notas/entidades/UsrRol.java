package com.notas.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by nydiarra on 06/05/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USR_ROL")
public class UsrRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol")
    private Integer idRol;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private List<UsrUsuarioRol> usrUsuarioRolList;

}
