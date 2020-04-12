/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;


import com.notas.dto.UsrRolDTO;
import com.notas.service.UsrRolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhon
 */
@RestController
@RequestMapping("/api/v.1/Roles")
@CrossOrigin(origins = "*")
public class UsrRolController {

    @Autowired
    private UsrRolService usrRolService;

    public UsrRolController(UsrRolService usrRolService) {
        this.usrRolService = usrRolService;
    }

    @PostMapping("/crearRol")
    public ResponseEntity<?> guardarRol(@RequestBody UsrRolDTO rol) {

        UsrRolDTO rolDot = usrRolService.consultarRol(rol.getIdRol());

        if (rolDot != null) {

            rol = usrRolService.guardarRol(rol);
        }

        rol = usrRolService.guardarRol(rol);

        if (rol == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(rol);
    }
    
    @GetMapping("/listarRoles")
    public ResponseEntity<?> listarRoles(){
        List<UsrRolDTO> roles = usrRolService.listarRoles();
        
        if(roles == null || roles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(roles);
    }
    
    @DeleteMapping("/eliminarRol/{idRol}")
    public ResponseEntity<?> eliminarRol(@PathVariable("idRol") Integer id){
        UsrRolDTO rol = usrRolService.consultarRol(id);
        
        if(rol != null){
            usrRolService.eliminarRol(id);
        }else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }
}
