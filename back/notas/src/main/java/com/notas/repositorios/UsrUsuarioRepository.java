/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.UsrUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author UTP
 */
@Repository
public interface UsrUsuarioRepository extends JpaRepository<UsrUsuario, Integer> {

    UsrUsuario findByNombreUsuario(String nombreUsuario);

    UsrUsuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);

    @Query(value = "WITH roles as (select id_rol\n"
            + "			from usr_rol \n"
            + "			where nombre = 'Estudiante'),\n"
            + "usuarioRoles as (select ro.*\n"
            + "			from roles us, usr_usuario_rol ro\n"
            + "			where ro.id_rol = us.id_rol),\n"
            + "usuarios as (select us.*\n"
            + "			from usr_usuario us, usuarioRoles usR\n"
            + "			where us.id_usuario = usR.user_id)\n"
            + "select us.*\n"
            + "from usuarios us\n"
            + "where not exists (select id_estudiante\n"
            + "				 from curso_estudiante cu\n"
            + "				 where us.id_usuario = cu.id_estudiante)", nativeQuery = true)
    List<UsrUsuario> estudianteSinCurso();
}
