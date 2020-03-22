/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;

import com.notas.dto.ActividadDTO;
import com.notas.service.ActividadService;
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
@RequestMapping("/api/v.1/actividad")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping("consultarPorCurso/{idCurso}")
    public ResponseEntity<?> consultarPorCurso(@PathVariable("idCurso") Integer idCurso) {
        List<ActividadDTO> res = actividadService.actividadesCurso(idCurso);
        return ResponseEntity.ok(res);
    }

    @PostMapping("guardarActividad")
    public ResponseEntity<?> guardarActividad(@RequestBody ActividadDTO actividad) {
        ActividadDTO res = actividadService.crearActividad(actividad);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("eliminarActividad/{id}")
    public ResponseEntity<?> eliminarActividad(@PathVariable("id") Integer id) {
        ActividadDTO res = actividadService.eliminarActividad(id);
        return ResponseEntity.ok(res);
    }
}
