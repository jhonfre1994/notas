/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.UsrRol;
import com.notas.entidades.UsrUsuarioRol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhon
 */
@Repository
public interface UsrUsuarioRolRepository extends JpaRepository<UsrUsuarioRol, Integer> {

    @Query(value = "select y.id, y.user_id, y.id_rol\n"
            + "from usr_usuario u, usr_usuario_rol y\n"
            + "where u.id_usuario = :id and y.user_id = :id", nativeQuery = true)

    List<UsrUsuarioRol> listarRoles(@Param("id") Integer id);

    @Query(value = "select *\n"
            + "from usr_usuario_rol ur\n"
            + "where ur.user_id = :idUser and ur.id_rol = :idRol", nativeQuery = true)
    List<UsrUsuarioRol> buscarRolAsignado(@Param("idUser") Integer idUser, @Param("idRol") Integer idRol);

    List<UsrUsuarioRol> findByIdRol(UsrRol rol);
}
