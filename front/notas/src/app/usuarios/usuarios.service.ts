import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { usuarioDTO } from '../dto/usuarioDTO';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }
  
  public listarUsuarios(): Observable<usuarioDTO[]> {
    return this.http.get<usuarioDTO[]>(environment.gatewayServer + "api/v.1/usuarios/")
  }

  public listarEstudiantes(): Observable<usuarioDTO[]> {
    return this.http.get<usuarioDTO[]>(environment.gatewayServer + "api/v.1/usuarios/listarEstudiantes")
  }

  public guardarUsuario(usu : usuarioDTO): Observable<usuarioDTO> {
    return this.http.post<usuarioDTO>(environment.gatewayServer + "api/v.1/usuarios/guardarUsuario", usu, this.header)
  }

  public eliminarUsuario(id: number): Observable<usuarioDTO> {
    return this.http.delete<usuarioDTO>(environment.gatewayServer + "api/v.1/usuarios/eliminarUsuario/" + id)

  }



}
