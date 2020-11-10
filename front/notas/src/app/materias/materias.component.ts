import { Component, OnInit } from '@angular/core';
import { DropDown } from '../usuarios/usuarios.component';
import { MateriasService } from './materias.service';
import { ThrowStmt } from '@angular/compiler';
import { MateriaDTO } from '../dto/MateriaDTO';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { CursosService } from '../cursos/cursos.service';
import { UsuariosService } from '../usuarios/usuarios.service';
import { usuarioDTO } from '../dto/usuarioDTO';
import { CursosDTO } from '../dto/CursosDTO';

@Component({
  selector: 'app-materias',
  templateUrl: './materias.component.html',
  styleUrls: ['./materias.component.css'],
  providers: [MateriasService, MessageService, ConfirmationService, CursosService, UsuariosService]
})
export class MateriasComponent implements OnInit {

  display: boolean = false;

  materias: Array<MateriaDTO> = [];
  materiasGuardad: MateriaDTO = new MateriaDTO();
  usuariosLis: Array<usuarioDTO> = new Array<usuarioDTO>();
  selectedUsr: usuarioDTO = new usuarioDTO();
  cursosList: Array<CursosDTO> = new Array<CursosDTO>();
  selectedCruso: CursosDTO = new CursosDTO()
  jornadas: Array<DropDown> = new Array<DropDown>();
  selectJornada: DropDown = new DropDown()
  cursoSelected: CursosDTO = new CursosDTO();

  ocultarBoton: boolean = true;
  constructor(private materiasService: MateriasService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private cursosService: CursosService,
    private usuariosService: UsuariosService) {
    this.jornadas = [
      { genero: 'Mañana' },
      { genero: 'Tarde' },
      { genero: 'Noche' },
      { genero: 'Sabatina' }
    ]
  }

  ngOnInit() {
    this.listarUsuarios()
    this.listarTodos()
  }

  listarMateriasCurso(idCurso: number) {
    this.materias = [];
    this.materiasService.listarMateriasSoloCurso(idCurso).subscribe(res => {
      console.log(res)
      this.materias = res
    },
      error => {
        this.showToast("error", "", error.error.message);
      })
  }

  showDialog() {
    //this.limpiarCampos()
    this.display = true;
  }

  listarUsuarios() {
    this.usuariosService.listarProfesores().subscribe(res => {
      if (res != null) {
        this.usuariosLis = res
      }
    })
  }

  listarCursosJornada(jornada: string) {
    this.cursosList = []
    this.cursosService.listarCursosJornada(jornada).subscribe(res => {
      if (res != null) {
        console.log(res)
        this.cursosList = res;
      }
    },
      error => {
        this.showToast("error", "", error.error.message);
      })
  }


  listarTodos() {
    this.cursosService.listarCursos().subscribe(res => {
      if (res != null) {
        this.cursosList = res;
      }
    },
      error => {
        this.showToast("error", "", error.error.message);

      })
  }

  public showToast(tipo: string, resumen: string, detalle: string): void {
    this.messageService.add({ severity: tipo, summary: resumen, detail: detalle })
  }

  public comprobarParametros(): boolean {
    if (this.materiasGuardad.nombre == null) {
      this.showToast("error", "Error de Atributos", "El nombre de la materia no puede estar vaciá");
      return false;
    }
    if (this.materiasGuardad.abreviatura == null) {
      this.showToast("error", "Error de Atributos", "La abreviatura no puede estar vaciá");
      return false;
    }
    if (this.materiasGuardad.profesor.idUsuario == null || this.materiasGuardad.profesor.idUsuario == undefined) {
      this.showToast("error", "Error de Atributos", "El profesor no puede estar vació");
      return false;
    }
    if (this.materiasGuardad.idCurso.idCurso == null || this.materiasGuardad.idCurso.idCurso == undefined) {
      this.showToast("error", "Error de Atributos", "El curso no puede estar vació");
      return false;
    }
    return true;
  }

  guardar() {

    this.materiasGuardad.profesor = this.selectedUsr
    this.materiasGuardad.idCurso = this.selectedCruso

    if (this.materiasGuardad.idMateria != 0) {
      this.materiasGuardad.idMateria = this.materiasGuardad.idMateria
    } else {
      this.materiasGuardad.idMateria = 0
    }
    console.log(this.materiasGuardad)
    if (this.comprobarParametros() == true) {
      this.materiasService.guardarMateria(this.materiasGuardad).subscribe(res => {
        if (res != null) {
          console.log(res)
          this.display = false;
          this.listarMateriasCurso(this.cursoSelected.idCurso);
          this.showToast("success", "Bien", "Materia guardad correctamente");
        }
        //this.listarMaterias()
      })
    }
  }

  buscarCursos(selectJornada: DropDown) {
    this.cursosList = []
    this.ocultarBoton = true;
    this.cursoSelected = new CursosDTO();
    this.materias = []
    this.listarCursosJornada(selectJornada.genero)
  }

  buscarMaterias(curso: CursosDTO) {
    this.ocultarBoton = false;
    this.listarMateriasCurso(curso.idCurso)
    this.selectedCruso = curso
  }

  editar(materia: MateriaDTO) {
    console.log(materia)
    this.display = true
    this.materiasGuardad = materia;
    this.selectedUsr= materia.profesor
    this.selectedUsr.nombreCompleto = materia.responsable
  }
}
