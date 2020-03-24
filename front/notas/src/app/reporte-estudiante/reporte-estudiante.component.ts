import { Component, OnInit } from '@angular/core';
import { RegistrarNotasService } from '../registrar-notas/registrar-notas.service';
import { CursosService } from '../cursos/cursos.service';
import { CursosDTO } from '../dto/CursosDTO';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { CursoEstudianteService } from '../curso-estudiante/curso-estudiante.service';
import { usuarioDTO } from '../dto/usuarioDTO';
import { RegistrarNotasDTO } from '../dto/RegistrarNotasDTO';

@Component({
  selector: 'app-reporte-estudiante',
  templateUrl: './reporte-estudiante.component.html',
  styleUrls: ['./reporte-estudiante.component.css'],
  providers: [CursoEstudianteService, CursosService, MessageService, ConfirmationService, RegistrarNotasService]
})
export class ReporteEstudianteComponent implements OnInit {

  cursosList: Array<CursosDTO> = new Array<CursosDTO>();
  cursoSelected: CursosDTO = new CursosDTO();


  usuariosSelected: usuarioDTO = new usuarioDTO();
  estudiantesCurso: Array<usuarioDTO> = new Array<usuarioDTO>();


  notasList: Array<RegistrarNotasDTO> = new Array<RegistrarNotasDTO>();
  constructor(private cursoEstudianteService: CursoEstudianteService,
    private cursosService: CursosService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private registrarNotasService: RegistrarNotasService) { }

  ngOnInit() {
    let usr = JSON.parse(sessionStorage.getItem("usuario"))
    this.listarCursos(usr.idUsuario)
  }

  listarCursos(IdProfesor: number) {
    this.cursosService.listarCursosPorfesor(IdProfesor).subscribe(res => {
      if (res != null) {
        this.cursosList = res
        console.log(res)
      }
    })
  }

  dropDownCurso($event) {
    this.estudiantesCurso = []
    this.estudiantesPorCurso($event.value.idCurso)
  }

  estudiantesPorCurso(idCurso: number) {
    this.cursoEstudianteService.estudiantesCursos(idCurso).subscribe(res => {
      console.log(res)
      this.estudiantesCurso = res;

    })
  }

  generarReporte() {
    this.notasList = []
    console.log(this.cursoSelected)
    console.log(this.usuariosSelected)
    this.registrarNotasService.reporteNotas(this.usuariosSelected.idUsuario).subscribe(res => {
      if (res != null) {
        this.notasList = res
      }
      console.log(res)
    })
  }

}
