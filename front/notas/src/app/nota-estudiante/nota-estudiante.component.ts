import { Component, OnInit } from '@angular/core';
import { NotaEstudianteService } from './nota-estudiante.service';
import { usuarioDTO } from '../dto/usuarioDTO';
import { NotaActividadDTO } from '../dto/NotaActividadDTO';

@Component({
  selector: 'app-nota-estudiante',
  templateUrl: './nota-estudiante.component.html',
  styleUrls: ['./nota-estudiante.component.css'],
  providers: [NotaEstudianteService]
})
export class NotaEstudianteComponent implements OnInit {

  notas: Array<NotaActividadDTO> = []
  constructor(private notaEstudianteService: NotaEstudianteService) { }

  ngOnInit() {
    let usu = new usuarioDTO;
    usu = JSON.parse(sessionStorage.getItem("usuario"))
    if (usu.rol == 'Estudiante') {
      this.notasEstudiante(usu.idUsuario)
    }
  }

  notasEstudiante(id: number) {
    this.notaEstudianteService.notasEstudiante(id).subscribe(res => {
      if (res != null) {
        this.notas = res;
        console.log(res)
      }
    },
      error => {
        console.log(error.error.message)
      })
  }


}
