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

  constructor(private materiasService: MateriasService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private cursosService: CursosService,
    private usuariosService: UsuariosService) {

  }

  ngOnInit() {
    this.listarMaterias()
    this.listarUsuarios()
    this.listarTodos()
  }

  listarMaterias() {
    this.materiasService.listarCursos().subscribe(res => {
      console.log(res)
      this.materias = res
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
    console.log(this.materiasGuardad)
    if (this.comprobarParametros() == true) {
      this.materiasService.guardarMateria(this.materiasGuardad).subscribe(res => {
        console.log(res)
        this.listarMaterias()
      })
    }

  }

}
