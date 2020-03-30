import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotaEstudianteService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }


  public notasEstudiante(idEstudiante: number): Observable<any[]> {
    return this.http.get<any[]>(environment.gatewayServer + "api/v.1/notas/notasEstudiante/" + idEstudiante)
  }

}
