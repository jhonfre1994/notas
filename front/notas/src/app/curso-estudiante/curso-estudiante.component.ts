import { Component, OnInit } from '@angular/core';
import { CursosService } from '../cursos/cursos.service';
import { CursosDTO } from '../dto/CursosDTO';
import { usuarioDTO } from '../dto/usuarioDTO';
import { UsuariosService } from '../usuarios/usuarios.service';
import { CursoEstudianteService } from './curso-estudiante.service';
import { SaveCursoEstudiante } from '../dto/SaveCursoEstudiante';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";


@Component({
  selector: 'app-curso-estudiante',
  templateUrl: './curso-estudiante.component.html',
  styleUrls: ['./curso-estudiante.component.css'],
  providers: [CursosService, UsuariosService, CursoEstudianteService, MessageService, ConfirmationService]
})
export class CursoEstudianteComponent implements OnInit {

  cursosList: Array<CursosDTO> = new Array<CursosDTO>();
  usuariosList: Array<usuarioDTO> = new Array<usuarioDTO>();
  cursoSelected: CursosDTO = new CursosDTO();
  usuariosSelected: Array<usuarioDTO> = new Array<usuarioDTO>();


  estudiantesCurso: Array<usuarioDTO> = new Array<usuarioDTO>();

  guardarDatos: SaveCursoEstudiante = new SaveCursoEstudiante();

  constructor(private cursosService: CursosService,
    private usuariosService: UsuariosService,
    private cursoEstudianteService: CursoEstudianteService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    let usr = JSON.parse(sessionStorage.getItem("usuario"))
    this.listarCursos(usr.idUsuario)
    this.listarUsuarios();
  }

  listarCursos(IdProfesor: number) {
    this.cursosService.listarCursosPorfesor(IdProfesor).subscribe(res => {
      if (res != null) {
        this.cursosList = res
        console.log(res)
      }
    })
  }

  public showToast(tipo: string, resumen: string, detalle: string): void {
    this.messageService.add({ severity: tipo, summary: resumen, detail: detalle })
  }

  public comprobarParametros(): boolean {
    if (this.guardarDatos.curso.idCurso == null) {
      this.showToast("error", "Error de Atributos", "Debe seleccionar un curso");
      return false;
    }
    if (this.guardarDatos.estudiantes.length == 0) {
      this.showToast("error", "Error de Atributos", "Debe seleccionar al menos un estudiante");
      return false;
    }
    return true;
  }


  guardar() {
    this.guardarDatos.curso = this.cursoSelected
    this.guardarDatos.estudiantes = this.usuariosSelected
    if (this.comprobarParametros() == true) {
      console.log(this.guardarDatos)
      this.cursoEstudianteService.asignarEstudiantes(this.guardarDatos).subscribe(res => {
        if (res != null) {
          console.log(res)
          delete this.guardarDatos
          this.guardarDatos = new SaveCursoEstudiante()
          this.estudiantesPorCurso(this.cursoSelected.idCurso)
        }
      })
    }
  }

  listarUsuarios() {
    this.usuariosService.listarEstudiantes().subscribe(res => {
      if (res != null) {
        this.usuariosList = res
        console.log(res)
      }
    })
  }

  dropDownCurso($event) {
    this.estudiantesCurso =[]
    this.estudiantesPorCurso($event.value.idCurso)
  }

  estudiantesPorCurso(idCurso: number) {
    this.cursoEstudianteService.estudiantesCursos(idCurso).subscribe(res => {
      if (res != null) {
        this.estudiantesCurso = res;
        console.log(res)
      }
    },
    error =>{
      this.showToast("error", "", error.error.message);
    })
  }

  confirmar(estudiante: usuarioDTO) {
    this.confirmationService.confirm({
      message: 'Esta seguro que desea eliminar es estudiante del curso ' + this.cursoSelected.nombreCurso + '?',
      accept: () => {
        this.eliminar(estudiante)
      }
    });
  }

  eliminar(estudiante: usuarioDTO){
    console.log(estudiante)
    console.log(this.cursoSelected)
    this.cursoEstudianteService.eliminarEstudiantes(estudiante.idUsuario,this.cursoSelected.idCurso).
    subscribe(res =>{
      if(res != null){
        this.estudiantesCurso =[]
        this.estudiantesPorCurso(this.cursoSelected.idCurso)
      }
    })

  }
}
