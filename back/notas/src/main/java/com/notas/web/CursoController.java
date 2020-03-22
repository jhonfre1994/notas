/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;

import com.notas.dto.CursoDTO;
import com.notas.service.CursoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author UTP
 */
@RestController
@RequestMapping("/api/v.1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping()
    public ResponseEntity<?> listarCursos() {
        List<CursoDTO> res = cursoService.listarCursos();

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("guardarCruso")
    public ResponseEntity<?> guardarCruso(@RequestBody CursoDTO curso) {
        CursoDTO res = cursoService.guardarCruso(curso);

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }
    
    
    @DeleteMapping("eliminarCurso/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable("id") Integer id) {
        CursoDTO res = cursoService.eliminarCrusto(id);

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

}
