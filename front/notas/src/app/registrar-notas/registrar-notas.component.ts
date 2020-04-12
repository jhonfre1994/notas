import { Component, OnInit } from '@angular/core';
import { CursosService } from '../cursos/cursos.service';
import { ActividadesService } from '../actividades/actividades.service';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { CursosDTO } from '../dto/CursosDTO';
import { ActividadDTO } from '../dto/ActividadDTO';
import { RegistrarNotasService } from './registrar-notas.service';
import { RegistrarNotasDTO } from '../dto/RegistrarNotasDTO';

@Component({
  selector: 'app-registrar-notas',
  templateUrl: './registrar-notas.component.html',
  styleUrls: ['./registrar-notas.component.css'],
  providers: [CursosService, ActividadesService, MessageService, ConfirmationService, RegistrarNotasService]
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
    private registrarNotasService: RegistrarNotasService) { }

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


  actividadesPorCurso(idCurso: number) {
    this.actividadesList = []
    this.actividadesService.actividadesPorCurso(idCurso).subscribe(res => {
      if (res != null) {
        this.actividadesList = res
      }
    },
      error => {
        this.showToast("error", "", error.error.message);
      })
  }

  dropDownCurso($event) {
    this.actividadesPorCurso($event.value.idCurso)
  }

  notasPorActividad(idActividad: number) {
    this.notas = []
    this.registrarNotasService.notasPorActividad(idActividad).subscribe(res => {
      if (res != null) {
        this.notas = res;
      }
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

}
