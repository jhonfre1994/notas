import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import {HttpClient, HttpHeaders} from "@angular/common/http"
import { Observable } from 'rxjs';
import { loginDTO } from '../dto/loginDTO';
import { usuarioDTO } from '../dto/usuarioDTO';

@Injectable()
export class LoginService {

  constructor(public http: HttpClient) { }

  header = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
  }

  public login(login : loginDTO):Observable<usuarioDTO>{
    return this.http.post<usuarioDTO>(environment.gatewayServer + "api/v.1/usuarios/login", login, this.header)
  }

  public consultarUsr(username : string):Observable<usuarioDTO>{
    return this.http.get<usuarioDTO>(environment.gatewayServer + "api/v.1/usuarios/consularPorUsuario/"+username)
  }

  
}
