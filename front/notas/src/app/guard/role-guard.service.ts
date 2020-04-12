import { Injectable } from '@angular/core';
import {
    Router,
    CanActivate,
    ActivatedRouteSnapshot
} from '@angular/router';
import { AuthService } from './auth.service';
import decode from 'jwt-decode';

@Injectable({
    providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

    constructor(public auth: AuthService, public router: Router) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {

        const expectedRole = route.data.expectedRole;
        const token = sessionStorage.getItem("access_token");
        const tokenPayload = decode(token);
        if (
            this.auth.isLoggin() &&
            this.validarRol(tokenPayload.authorities, expectedRole) == true) {
            return true;
        }
        return false;
    }


    validarRol(lista: Array<any>, roleEsperado: Array<any>): boolean {
        const found = lista.some(r => roleEsperado.indexOf(r) >= 0)
        return found;
    }


}
