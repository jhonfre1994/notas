import { Injectable, Inject } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { usuarioDTO } from '../dto/usuarioDTO';
import { JwtHelperService } from '@auth0/angular-jwt';
import decode from 'jwt-decode';
import { tokenNotExpired } from 'angular2-jwt';

@Injectable({
    providedIn: 'root'
})
export class AuthService implements CanActivate {

    token = localStorage.getItem("access_token");
    refres_token = localStorage.getItem("refresh_token");
    usuarioLog = localStorage.getItem("usuario");



    constructor(public jwtHelper: JwtHelperService, private router: Router,
        @Inject('tokenNotExpired') private tokenNotExpired) { }

    canActivate() {
        // If the user is not logged in we'll send them back to the home page
        let usr = new usuarioDTO();
        usr = JSON.parse(sessionStorage.getItem("usuario"))
        if (usr == null || usr == undefined) {
            this.router.navigate(['/login']);
            return false;
        }
        return true;
    }

    isLoggin(): boolean {
        const token = sessionStorage.getItem("access_token");
        const refres_token = sessionStorage.getItem("refresh_token");
        const usuarioLog = sessionStorage.getItem("usuario");
        if (refres_token === null || this.jwtHelper.isTokenExpired(refres_token) == true
            || token === null || usuarioLog === null) {
            this.router.navigateByUrl('login')
            return false;
        }
        return true;
    }
}