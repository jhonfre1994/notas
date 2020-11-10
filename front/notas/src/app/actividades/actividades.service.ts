import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActividadDTO } from '../dto/ActividadDTO';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ActividadesService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }

  public actividadesPorMateria(idMaterial: number): Observable<ActividadDTO[]> {
    return this.http.get<ActividadDTO[]>(environment.gatewayServer + "api/v.1/actividad/activiadesPorMateria/" + idMaterial)
  }

  public guardarActividad(actividad: ActividadDTO): Observable<ActividadDTO> {
    return this.http.post<ActividadDTO>(environment.gatewayServer + "api/v.1/actividad/guardarActividad", actividad, this.header)
  }

  public eliminarActividad(idActividad: number): Observable<ActividadDTO> {
    return this.http.delete<ActividadDTO>(environment.gatewayServer + "api/v.1/actividad/eliminarActividad/"+idActividad)
  }

}
