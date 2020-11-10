import { Component, OnInit } from '@angular/core';
import { CursosDTO } from '../dto/CursosDTO';
import { CursosService } from '../cursos/cursos.service';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { ActividadDTO } from '../dto/ActividadDTO';
import { ActividadesService } from './actividades.service';
import { MateriaDTO } from '../dto/MateriaDTO';
import { MateriasService } from '../materias/materias.service';

@Component({
  selector: 'app-actividades',
  templateUrl: './actividades.component.html',
  styleUrls: ['./actividades.component.css'],
  providers: [CursosService, MessageService, ConfirmationService, ActividadesService, MateriasService]
})
export class ActividadesComponent implements OnInit {

  cursosList: Array<CursosDTO> = new Array<CursosDTO>();
  cursoSelected: CursosDTO = new CursosDTO();
  guardarActividad: ActividadDTO = new ActividadDTO();
  actividadesList: Array<ActividadDTO> = new Array<ActividadDTO>();
  ocultarBoton: boolean = true;
  materias: Array<MateriaDTO> = [];
  selectedCruso: CursosDTO = new CursosDTO()
  materiaSelected : MateriaDTO = new MateriaDTO();
  usuario: any;

  constructor(private cursosService: CursosService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private actividadesService: ActividadesService,
    private materiasService: MateriasService) { }

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

  limpiarDatos() {
    delete this.guardarActividad
    this.guardarActividad = new ActividadDTO();
  }

  public comprobarParametros(): boolean {
    if (this.guardarActividad.idMateria == null) {
      this.showToast("error", "Error de Atributos", "Debe seleccionar una materia");
      return false;
    }
    if (this.guardarActividad.nombreActividad == null) {
      this.showToast("error", "Error de Atributos", "El nombre de la actividad no puede estar vació");
      return false;
    }
    return true;
  }

  guardar() {
    this.guardarActividad.idMateria = this.materiaSelected
    if (this.comprobarParametros() == true) {
      this.actividadesService.guardarActividad(this.guardarActividad).subscribe(res => {
        if (res != null) {
          this.actividadesPorMateria(this.materiaSelected.idMateria)
          this.limpiarDatos()
        }
      })
    }
  }

  editar(actividad: ActividadDTO) {
    this.guardarActividad = actividad
  }

  dropDownCurso($event) {
    this.listarMateriasCurso($event.value.idCurso);
    //this.actividadesPorCurso($event.value.idCurso)
  }

  actividadesPorMateria(idCurso: number) {
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
          this.actividadesPorMateria(this.materiaSelected.idMateria)
        }
      })

  }

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

  buscarMaterias(materia: MateriaDTO) {
    this.ocultarBoton = false;
    this.actividadesPorMateria(materia.idMateria)
    this.materiaSelected = materia
  }
}
