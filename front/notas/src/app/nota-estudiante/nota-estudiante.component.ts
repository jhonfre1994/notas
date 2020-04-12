import { Component, OnInit, LOCALE_ID } from '@angular/core';
import { NotaEstudianteService } from './nota-estudiante.service';
import { usuarioDTO } from '../dto/usuarioDTO';
import { NotaActividadDTO } from '../dto/NotaActividadDTO';
import localeEsAr from '@angular/common/locales/es-AR';
import { registerLocaleData } from '@angular/common';


registerLocaleData(localeEsAr, 'es-Ar');

@Component({
  selector: 'app-nota-estudiante',
  templateUrl: './nota-estudiante.component.html',
  styleUrls: ['./nota-estudiante.component.css'],
  providers: [NotaEstudianteService,
    { provide: LOCALE_ID, useValue: 'es-Ar' }]
})
export class NotaEstudianteComponent implements OnInit {

  notas: Array<NotaActividadDTO> = []
  usu: usuarioDTO = new usuarioDTO();
  fechaActual: Date = new Date();
  constructor(private notaEstudianteService: NotaEstudianteService) { }

  ngOnInit() {
    this.usu = JSON.parse(sessionStorage.getItem("usuario"))
    this.notasEstudiante(this.usu.idUsuario)
  }

  notasEstudiante(id: number) {
    this.notaEstudianteService.notasEstudiante(id).subscribe(res => {
      if (res != null) {
        this.notas = res;
      }
    },
      error => {
        console.log(error.error.message)
      })
  }


}
