/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;

import com.notas.dto.MateriaDTO;
import com.notas.service.MateriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/v.1/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping()
    public ResponseEntity<?> listarMaterias() {
        List<MateriaDTO> res = materiaService.listarMaterias();

        return ResponseEntity.ok(res);
    }
    
    @GetMapping("listarMateriasCruso/{idCurso}/{idProfesor}")
    public ResponseEntity<?> listarMateriasCruso(@PathVariable("idCurso")Integer idCurso, 
            @PathVariable("idProfesor")Integer idProfesor) {
        List<MateriaDTO> res = materiaService.materiasPorCurso(idCurso, idProfesor);
        return ResponseEntity.ok(res);
    }

    @PostMapping()
    public ResponseEntity<?> guardarMateria(@RequestBody MateriaDTO materia) {
        MateriaDTO res = materiaService.guardarMateria(materia);
        return ResponseEntity.ok(res);
    }
    
    @GetMapping("listarMateriasCruso/{idCurso}")
    public ResponseEntity<?> listarMateriasDelCurso(@PathVariable("idCurso")Integer idCurso) {
        List<MateriaDTO> res = materiaService.materiasPorSoloCurso(idCurso);
        return ResponseEntity.ok(res);
    }
}
