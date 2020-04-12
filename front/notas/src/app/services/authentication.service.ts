import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { usuarioDTO } from '../dto/usuarioDTO';


@Injectable()
export class AuthenticationService {
  static AUTH_TOKEN = '/oauth/token';

  constructor(private http: Http, private http2: HttpClient) {
  }

  header = {
    headers: new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem(environment.TOKEN_NAME)}`)
      .set('Content-Type', 'application/json')
  }


  login(username: string, password: string) {
    const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;

    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':'
      + environment.TOKEN_AUTH_PASSWORD));

    return this.http.post(environment.gatewayServer + "oauth/token", body, { headers })
      .pipe(map(res => res.json()))
      .pipe(map((res: any) => {
        if (res) {
          return res;
        }
        return null;
      }));
  }

  refreshToken() {
    var refresh = localStorage.getItem('refresh_token');
    const body = `&refresh_token=${refresh}&grant_type=refresh_token`;
    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' +
      environment.TOKEN_AUTH_PASSWORD));


    return this.http.post(environment.gatewayServer + "oauth/token", body, { headers })

      .pipe(map(res => res.json()))

      .subscribe(data => {
        localStorage.setItem("refresh_token", data.refresh_token);
        localStorage.setItem(environment.TOKEN_NAME, data.access_token);

      })
  }


  prueba() {
    var header = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${localStorage.getItem(environment.TOKEN_NAME)}`)
    }
    return this.http2.get(environment.gatewayServer + "user/me", header)
      .subscribe(res => {
      }
      )
  };


  iniciarSesion(usrname: string): Observable<usuarioDTO> {
    var baseUrl = environment.gatewayServer + 'api/v.1/Usuarios/iniciarSesion';
    return this.http2.get<usuarioDTO>(`${baseUrl}/${usrname}`);
  }




}
