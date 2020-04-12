import { Component, OnInit } from '@angular/core';
import { CursosDTO } from '../dto/CursosDTO';
import { CursosService } from '../cursos/cursos.service';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { ActividadDTO } from '../dto/ActividadDTO';
import { ActividadesService } from './actividades.service';

@Component({
  selector: 'app-actividades',
  templateUrl: './actividades.component.html',
  styleUrls: ['./actividades.component.css'],
  providers: [CursosService, MessageService, ConfirmationService, ActividadesService]
})
export class ActividadesComponent implements OnInit {

  cursosList: Array<CursosDTO> = new Array<CursosDTO>();
  cursoSelected: CursosDTO = new CursosDTO();
  guardarActividad: ActividadDTO = new ActividadDTO();
  actividadesList: Array<ActividadDTO> = new Array<ActividadDTO>();

  constructor(private cursosService: CursosService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private actividadesService: ActividadesService) { }

  ngOnInit() {
    let usr = JSON.parse(sessionStorage.getItem("usuario"))
    this.listarCursos(usr.idUsuario)
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

  limpiarDatos() {
    delete this.guardarActividad
    this.guardarActividad = new ActividadDTO();
  }

  public comprobarParametros(): boolean {
    if (this.guardarActividad.idCurso.idCurso == null) {
      this.showToast("error", "Error de Atributos", "Debe seleccionar un curso");
      return false;
    }
    if (this.guardarActividad.nombreActividad == null) {
      this.showToast("error", "Error de Atributos", "El nombre de la actividad no puede estar vació");
      return false;
    }
    return true;
  }

  guardar() {
    this.guardarActividad.idCurso = this.cursoSelected
    if (this.comprobarParametros() == true) {
      this.actividadesService.guardarActividad(this.guardarActividad).subscribe(res => {
        if (res != null) {
          this.actividadesPorCurso(this.cursoSelected.idCurso)
          this.limpiarDatos()
        }
      })
    }
  }

  editar(actividad: ActividadDTO) {
    this.guardarActividad = actividad
  }

  dropDownCurso($event) {
    this.actividadesPorCurso($event.value.idCurso)
  }

  actividadesPorCurso(idCurso: number) {
    this.actividadesList =[]
    this.actividadesService.actividadesPorCurso(idCurso).subscribe(res => {
      if (res != null) {
        this.actividadesList = res
      }
    },
      error => {
        this.showToast("error", "", error.error.message);
      })
  }

  confirmar(actividad: ActividadDTO) {
    this.confirmationService.confirm({
      message: 'Esta seguro que desea eliminar la actividad' + actividad.nombreActividad + '?',
      accept: () => {
        this.eliminar(actividad)
      }
    });
  }

  eliminar(actividad: ActividadDTO) {
    this.actividadesService.eliminarActividad(actividad.idActividad).
      subscribe(res => {
        if (res != null) {
          this.actividadesList = []
          this.actividadesPorCurso(this.cursoSelected.idCurso)
        }
      })

  }
}
