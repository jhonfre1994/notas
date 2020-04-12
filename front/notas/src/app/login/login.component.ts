import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { loginDTO } from '../dto/loginDTO';
import { MessageService, Message } from 'primeng/api';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';
import { delay } from 'rxjs/operators';
import { RoleGuardService } from '../guard/role-guard.service';
import decode from 'jwt-decode';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService, MessageService, AuthenticationService, UserService]
})
export class LoginComponent implements OnInit {

  msgs: Message[] = [];
  loginAccess: loginDTO = new loginDTO();
  constructor(private loginService: LoginService,
    private messageService: MessageService,
    public router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private roleGuardService: RoleGuardService) { }

  ngOnInit() {
    sessionStorage.clear();
  }

  validar() {

  }

  login2() {
    this.loginService.login(this.loginAccess).subscribe(res => {
      if (res != null) {
        res.contrasena = ''
        sessionStorage.setItem("usuario", JSON.stringify(res))
        if (res.rol == 'Profesor') {
          this.navigateAfterSuccess()
        } else {
          this.navigateVerNotas()
        }

      }
    },
      error => {
        this.show(error.error.message)
        console.log(error.error.message)
      })
  }

  show(error: string) {
    this.msgs = [];
    this.msgs.push({ severity: 'error', summary: 'Error', detail: error });
  }

  hide() {
    this.msgs = [];
  }

  navigateAfterSuccess() {
    this.router.navigate(['registrarNotas'])
  }

  navigateVerNotas() {
    this.router.navigate(['notasEstudiante'])
  }


  login() {
    this.authenticationService.login(this.loginAccess.username, this.loginAccess.password)
      .subscribe(
        result => {
          if (result) {
            this.userService.SavesessionStorage(result.access_token, result.refresh_token);
            this.loginService.consultarUsr(this.loginAccess.username).subscribe(res => {

              if (res != null) {
                res.contrasena = ''
                res.roles = []
                sessionStorage.setItem("usuario", JSON.stringify(res))
                this.navigateAfterSuccess();
                const token = sessionStorage.getItem("access_token");
                const tokenPayload = decode(token);
                if (this.roleGuardService.validarRol(tokenPayload.authorities, ["Estudiante"]) == true) {
                  this.navigateVerNotas();
                }
                if (this.roleGuardService.validarRol(tokenPayload.authorities, ["Profesor", "Administrador"]) == true) {
                  this.navigateAfterSuccess();
                }
              }
            })
          } else {
            /* this.IsWait = false;
            this.openSnackBar("El nombre de ususario o contraseña estan incorrectos", "!Cuidado¡"); */
          }
        },
        error => {
          let body = JSON.parse(error._body)
          /* if (body.error_description === 'UserDetailsService returned null, which is an interface contract violation') {
            this.openSnackBar("El usuario esta inactivo", "!Cuidado¡");
          } else {
            this.openSnackBar("El nombre de ususario o contraseña estan incorrectos", "!Cuidado¡");
          }
          this.IsWait = false; */
          if (body.error_description === 'Bad credentials') {
            this.show("Usuario y/o contraseña incorrectos")
          }
        }
      );
  }
}
