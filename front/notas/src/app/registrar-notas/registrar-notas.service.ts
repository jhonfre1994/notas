import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RegistrarNotasDTO } from '../dto/RegistrarNotasDTO';

@Injectable({
  providedIn: 'root'
})
export class RegistrarNotasService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }


  public notasPorActividad(idActividad: number): Observable<RegistrarNotasDTO[]> {
    return this.http.get<RegistrarNotasDTO[]>(environment.gatewayServer + "api/v.1/notas/notasActividad/" + idActividad)
  }


  public actualizarNota(idActividad: RegistrarNotasDTO): Observable<RegistrarNotasDTO> {
    return this.http.post<RegistrarNotasDTO>(environment.gatewayServer + "api/v.1/notas/actualizarNota", idActividad, this.header)
  }


}
