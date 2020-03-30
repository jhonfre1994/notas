import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { loginDTO } from '../dto/loginDTO';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService, MessageService]
})
export class LoginComponent implements OnInit {

  loginAccess: loginDTO = new loginDTO();
  constructor(private loginService: LoginService,
    private messageService: MessageService,
    public router: Router) { }

  ngOnInit() {
    sessionStorage.clear();
  }

  validar() {

  }

  login() {
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
    this.messageService.add({ severity: 'error', summary: 'Error', detail: error });
  }

  navigateAfterSuccess() {
    this.router.navigate(['registrarNotas'])
  }

  navigateVerNotas() {
    this.router.navigate(['notasEstudiante'])
  }

}
