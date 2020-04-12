/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;


import com.notas.dto.UsrRolDTO;
import com.notas.dto.UsrUsuarioRolDTO;
import com.notas.service.UsrUsuarioRolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhon
 */
@RestController
@RequestMapping("/api/v.1/UsrUsuarioRol")
@CrossOrigin(origins = "*")
public class UsrUsuarioRolController {

    @Autowired
    private UsrUsuarioRolService usrUsuarioRolService;

    public UsrUsuarioRolController(UsrUsuarioRolService usrUsuarioRolService) {
        this.usrUsuarioRolService = usrUsuarioRolService;
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<?> rolesUsuario(@PathVariable("id") Integer id) {
        List<UsrUsuarioRolDTO> acciones = usrUsuarioRolService.listarRolesUsu(id);

        if (acciones == null || acciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(acciones);
    }
    
    @PostMapping("/guardarRolUsuario")
    public ResponseEntity<?> guardarRolUsuario(@RequestBody UsrUsuarioRolDTO dto){
        UsrUsuarioRolDTO usrRol = usrUsuarioRolService.consolsultar(dto.getId());
        
        if(usrRol != null){
            return ResponseEntity.badRequest().build();
        }
        
        dto = usrUsuarioRolService.guardarUsrUsuRol(dto);
        
        if(dto == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(dto);
    }
    
    @DeleteMapping("/eliminarRolAsignado/{idusr}/{idRol}")
    public ResponseEntity<?> eliminarRolAsignado(@PathVariable("idusr") Integer idusr,
            @PathVariable("idRol") Integer idRol){
        UsrUsuarioRolDTO usrRol = usrUsuarioRolService.eliminarUsrUsuRol(idusr, idRol);
        
        if(usrRol == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(usrRol);
    }
    
//    @PutMapping("/eliminarRolesAsignado/{username}")
//    public ResponseEntity<?> actualizarRoles(@PathVariable("username") String username, @RequestBody List<UsrRolDTO> roles) {
//        List<UsrUsuarioRolDTO> rolesUsr = usrUsuarioRolService.actualizarRoles(roles, username);
//        if(rolesUsr == null  || roles.isEmpty()){
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(roles);
//    }
}
