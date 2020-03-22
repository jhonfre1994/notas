/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;

import com.notas.dto.CursoEstudianteDTO;
import com.notas.dto.UsrUsuarioDTO;
import com.notas.service.CursoEstudianteService;
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
 * @author UTP
 */
@RestController
@RequestMapping("/api/v.1/cursoEstudiante")
@CrossOrigin(origins = "*")
public class CursoEstudiantesController {

    @Autowired
    private CursoEstudianteService estudianteService;

    public CursoEstudiantesController(CursoEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("listarEstudiantes/{idCurso}")
    public ResponseEntity<?> estudiantesCurso(@PathVariable("idCurso") Integer idCurso) {
        List<UsrUsuarioDTO> res = estudianteService.listaPorCurso(idCurso);

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        List<CursoEstudianteDTO> res = estudianteService.listarTodos();

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("eliminarEstudianteCruso/{idCurso}/{idEstudiante}")
    public ResponseEntity<?> eliminarEstudianteCruso(@PathVariable("idCurso") Integer idCurso,
            @PathVariable("idEstudiante") Integer idEstudiante) {

        CursoEstudianteDTO res = estudianteService.eliminarEstudianteCrusto(idCurso, idEstudiante);

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("guardarEstudianteCurso")
    public ResponseEntity<?> guardarEstudianteCurso(@RequestBody CursoEstudianteDTO obj) {
        CursoEstudianteDTO res = estudianteService.guardarCursoEstudiante(obj);

        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.noContent().build();
    }

}
