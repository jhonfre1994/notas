import { Component, OnInit } from '@angular/core';
import { CursosService } from './cursos.service';
import { CursosDTO } from '../dto/CursosDTO';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { usuarioDTO } from '../dto/usuarioDTO';
import { UsuariosService } from '../usuarios/usuarios.service';


@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.css'],
  providers: [CursosService, MessageService, ConfirmationService, UsuariosService]
})
export class CursosComponent implements OnInit {

  guardarCurso: CursosDTO = new CursosDTO();
  cursosList: Array<CursosDTO> = new Array<CursosDTO>();
  usuariosLis: Array<usuarioDTO> = new Array<usuarioDTO>();
  display: boolean = false;
  selectedUsr: usuarioDTO = new usuarioDTO();

  constructor(private cursosService: CursosService,
    private usuariosService: UsuariosService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.listarTodos()
    this.listarUsuarios()
  }

  listarTodos() {
    this.cursosService.listarCursos().subscribe(res => {
      if (res != null) {
        this.cursosList = res;
        console.log(res)
      }
    },
    error =>{
      this.showToast("error", "", error.error.message);

    })
  }

  listarUsuarios() {
    this.usuariosService.listarProfesores().subscribe(res => {
      if (res != null) {
        console.log(res)
        this.usuariosLis = res
      }
    })
  }


  showDialog() {
    this.limpiarCampos()
    this.display = true;
  }

  public showToast(tipo: string, resumen: string, detalle: string): void {
    this.messageService.add({ severity: tipo, summary: resumen, detail: detalle })
  }

  public comprobarParametros(): boolean {
    if (this.guardarCurso.nombreCurso == null) {
      this.showToast("error", "Error de Atributos", "El nombre del curso no puede estar vació");
      return false;
    }
    if (this.guardarCurso.codigo == null) {
      this.showToast("error", "Error de Atributos", "El código del  no puede estar vació");
      return false;
    }
    if (this.guardarCurso.idProfesor == null || this.guardarCurso.idProfesor.idUsuario == undefined) {
      this.showToast("error", "Error de Atributos", "El responsable no puede estar vació");
      return false;
    }
    return true;
  }


  guardar() {
    this.guardarCurso.idProfesor = this.selectedUsr
    if (this.guardarCurso.idCurso != 0) {
      this.guardarCurso.idCurso = this.guardarCurso.idCurso
    } else {
      this.guardarCurso.idCurso = 0
    }

    if (this.comprobarParametros() == true) {
      this.cursosService.gurdarCurso(this.guardarCurso).subscribe(res => {
        if (res != null) {
          this.display = false
          this.listarTodos();
          console.log(res)
          this.showToast("success", "Bien", "Curso guardado correctamente");

        }
        this.limpiarCampos()
      })
    }

  }

  limpiarCampos() {
    delete this.guardarCurso
    this.guardarCurso = new CursosDTO
    delete this.selectedUsr
    this.selectedUsr = new usuarioDTO
  }

  editar(curso: CursosDTO) {
    console.log(curso)
    this.guardarCurso = curso
    this.display = true

    this.selectedUsr = curso.idProfesor
    this.selectedUsr.nombreCompleto = curso.responsable
  }

  confirmar(curso: CursosDTO) {
    this.confirmationService.confirm({
      message: 'Esta seguro que desea eliminar el curso?',
      accept: () => {
        this.eliminarCurso(curso)
      }
    });
  }

  eliminarCurso(curso: CursosDTO) {
    this.cursosList =[]
    this.cursosService.eliminarCurso(curso.idCurso).subscribe(res => {
      if (res != null) {
        this.listarTodos()
        this.showToast("success", "Bien", "Curso eliminado correctamente");
      }
    })
  }
}
