import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MateriaDTO } from '../dto/MateriaDTO';

@Injectable({
  providedIn: 'root'
})
export class MateriasService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }

  public listarCursos(): Observable<MateriaDTO[]> {
    return this.http.get<MateriaDTO[]>(environment.gatewayServer + "api/v.1/materias")
  }

  public guardarMateria(materia: MateriaDTO): Observable<MateriaDTO> {
    return this.http.post<MateriaDTO>(environment.gatewayServer + "api/v.1/materias", materia, this.header)
  }

}
