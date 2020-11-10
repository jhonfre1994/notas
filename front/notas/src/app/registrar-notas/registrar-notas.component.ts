import { Component, OnInit } from '@angular/core';
import { CursosService } from '../cursos/cursos.service';
import { ActividadesService } from '../actividades/actividades.service';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { CursosDTO } from '../dto/CursosDTO';
import { ActividadDTO } from '../dto/ActividadDTO';
import { RegistrarNotasService } from './registrar-notas.service';
import { RegistrarNotasDTO } from '../dto/RegistrarNotasDTO';
import { MateriaDTO } from '../dto/MateriaDTO';
import { MateriasService } from '../materias/materias.service';

@Component({
  selector: 'app-registrar-notas',
  templateUrl: './registrar-notas.component.html',
  styleUrls: ['./registrar-notas.component.css'],
  providers: [CursosService, ActividadesService, MessageService, ConfirmationService, RegistrarNotasService, MateriasService]
})
export class RegistrarNotasComponent implements OnInit {

  cursosList: Array<CursosDTO> = new Array<CursosDTO>();
  cursoSelected: CursosDTO = new CursosDTO();

  actividadesList: Array<ActividadDTO> = new Array<ActividadDTO>();
  actividadesSelected: ActividadDTO = new ActividadDTO();

  notas: Array<RegistrarNotasDTO> = new Array<RegistrarNotasDTO>();

  constructor(private cursosService: CursosService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private actividadesService: ActividadesService,
    private registrarNotasService: RegistrarNotasService,
    private materiasService: MateriasService) { }
    usuario: any;

  ngOnInit() {
    this.usuario = JSON.parse(sessionStorage.getItem("usuario"))
    this.listarCursos(this.usuario.idUsuario)
  }

  public showToast(tipo: string, resumen: string, detalle: string): void {
    this.messageService.add({ severity: tipo, summary: resumen, detail: detalle })
  }

  listarCursos(IdProfesor: number) {
    this.cursosService.listarCursosPorfesor(IdProfesor).subscribe(res => {
      if (res != null) {
        this.cursosList = res
      }
    })
  }


  actividadesPorCurso(idCurso: number) {
    this.actividadesList = []
    this.actividadesService.actividadesPorMateria(idCurso).subscribe(res => {
      if (res != null) {
        this.actividadesList = res
      }
    },
      error => {
        this.showToast("error", "", error.error.message);
      })
  }

  dropDownCurso($event) {
    console.log($event.value)
    this.listarMateriasCurso($event.value.idCurso);
    //this.actividadesPorCurso($event.value.idCurso)
  }

  materias: Array<MateriaDTO> = [];

  
  listarMateriasCurso(idCurso: number) {
    this.materias = [];
    this.materiasService.listarMateriasCurso(idCurso, this.usuario.idUsuario).subscribe(res => {
      console.log(res)
      this.materias = res
    },
      error => {
        this.showToast("error", "", error.error.message);
      })
  }


  notasPorActividad(idActividad: number) {
    this.notas = []
    this.registrarNotasService.notasPorActividad(idActividad).subscribe(res => {
      if (res != null) {
        this.notas = res;
      }
    },
    error => {
      this.showToast("error", "", error.error.message);
    })
  }

  buscar() {
    this.notasPorActividad(this.actividadesSelected.idActividad)
  }

  guardarNotas(nota: RegistrarNotasDTO) {
    this.registrarNotasService.actualizarNota(nota).subscribe(res => {
      if (res != null) {
        this.showToast("success", "Bien", "Nota guardada correctamente");
      }
    })
  }

  buscarMaterias(materia: MateriaDTO){
    console.log(materia)
    this.actividadesPorCurso(materia.idMateria)
  }
}
