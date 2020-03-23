import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { usuarioDTO } from '../dto/usuarioDTO';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SaveCursoEstudiante } from '../dto/SaveCursoEstudiante';

@Injectable({
  providedIn: 'root'
})
export class CursoEstudianteService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }

  public estudiantesCursos(idCurso:number): Observable<usuarioDTO[]> {
    return this.http.get<usuarioDTO[]>(environment.gatewayServer + "api/v.1/cursoEstudiante/listarEstudiantes/"+idCurso)
  }

  public asignarEstudiantes(datos: SaveCursoEstudiante): Observable<usuarioDTO[]>{
    return this.http.post<usuarioDTO[]>(environment.gatewayServer + "api/v.1/cursoEstudiante/guardarEstudianteCurso", datos, this.header)
  }
  
  public eliminarEstudiantes(idEstudiante: number, idCurso: number): Observable<usuarioDTO[]>{
    return this.http.delete<usuarioDTO[]>(environment.gatewayServer + 
      "api/v.1/cursoEstudiante/eliminarEstudianteCruso/"+idCurso+"/"+idEstudiante)
  }
}
