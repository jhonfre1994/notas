import { Injectable } from '@angular/core';
import { JwtHelper } from 'angular2-jwt';
import { AuthenticationService } from './authentication.service';
import { usuarioDTO } from '../dto/usuarioDTO';

@Injectable()
export class UserService {
  jwtHelper: JwtHelper = new JwtHelper();
  accessToken: string;
  isAdmin: boolean;

  constructor(private authenticationService: AuthenticationService) {
  }

  SavesessionStorage(accessToken: string, refre: string) {
    const decodedToken = this.jwtHelper.decodeToken(accessToken);
    /* console.log(accessToken);
    console.log(refre); */
    //this.isAdmin = decodedToken.authorities.some(el => el === 'ADMIN_USER');
    //console.log(decodedToken.authorities)
    this.accessToken = accessToken;
    sessionStorage.setItem('access_token', accessToken);
    sessionStorage.setItem("refresh_token", refre);
    //localStorage.setItem("usuarioLog", JSON.stringify(username));

  }


  logout() {
    this.accessToken = null;
    this.isAdmin = false;
    localStorage.clear()
  }

  isAdminUser(): boolean {
    return this.isAdmin;
  }

  isUser(): boolean {
    return this.accessToken && !this.isAdmin;
  }
}
