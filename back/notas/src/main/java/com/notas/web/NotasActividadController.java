/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notas.web;

import com.notas.dto.NotaActividadDTO;
import com.notas.service.NotaActividadService;
import java.util.ArrayList;
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
@RequestMapping("/api/v.1/notas")
@CrossOrigin(origins = "*")
public class NotasActividadController {

    @Autowired
    private NotaActividadService notaActividadService;

    public NotasActividadController(NotaActividadService notaActividadService) {
        this.notaActividadService = notaActividadService;
    }

    @GetMapping("notasActividad/{idActividad}")
    public ResponseEntity<?> notasActividad(@PathVariable("idActividad") Integer idActividad) {
        List<NotaActividadDTO> notasRes = notaActividadService.listarNotas(idActividad);
        return ResponseEntity.ok(notasRes);
    }

    @PostMapping("actualizarNota")
    public ResponseEntity<?> actualizarNota(@RequestBody NotaActividadDTO nota) {
        NotaActividadDTO notaRes = notaActividadService.actualizarNota(nota);
        return ResponseEntity.ok(notaRes);
    }

    @GetMapping("reporteNotas/{idEstudiante}")
    public ResponseEntity<?> reporteNotas(@PathVariable("idEstudiante") Integer idEstudiante) {
        List<NotaActividadDTO> notasRes = notaActividadService.reporteNotas(idEstudiante);
        return ResponseEntity.ok(notasRes);
    }
}
