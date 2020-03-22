/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.repositorios;

import com.notas.entidades.UsrUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author UTP
 */
@Repository
public interface UsrUsuarioRepository extends JpaRepository<UsrUsuario, Integer>{
    
    UsrUsuario findByNombreUsuario(String nombreUsuario);
    
}
