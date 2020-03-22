import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CursosDTO } from '../dto/CursosDTO';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CursosService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }
  
  public listarCursos(): Observable<CursosDTO[]> {
    return this.http.get<CursosDTO[]>(environment.gatewayServer + "api/v.1/cursos")
  }

  public gurdarCurso(curso: CursosDTO):Observable<CursosDTO>{
    return this.http.post<CursosDTO>(environment.gatewayServer +"api/v.1/cursos/guardarCruso", curso, this.header )
  }

  public eliminarCurso(curso: number):Observable<CursosDTO>{
    return this.http.delete<CursosDTO>(environment.gatewayServer +"api/v.1/cursos/eliminarCurso/" + curso)
  }
}
