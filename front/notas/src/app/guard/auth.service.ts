import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { usuarioDTO } from '../dto/usuarioDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthService  implements CanActivate{
  
    constructor(private router: Router) { }

    canActivate() {
        // If the user is not logged in we'll send them back to the home page
        let usr = new usuarioDTO();
        usr = JSON.parse(sessionStorage.getItem("usuario"))
        if (usr == null || usr == undefined ) {
            console.log('No estás logueado');
            this.router.navigate(['/login']);
            return false;
        }
        return true;
    }
}