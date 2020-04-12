import { Component, OnInit, AfterViewInit, Version } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd, ChildActivationStart } from '@angular/router';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { SelectItem, MenuItem } from 'primeng/api';
import decode from 'jwt-decode';
import { RoleGuardService } from '../guard/role-guard.service';

export class Veriones {
  valor: number;
}

@Component({
  selector: 'anh-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  providers: []
})
export class MenuComponent implements OnInit, AfterViewInit {

  items: MenuItem[];

  constructor(public router: Router, private _route: ActivatedRoute,
    private roleGuardService: RoleGuardService) {
  }

  ngOnInit() {
    let responsable = JSON.parse(sessionStorage.getItem("usuario"))
    const token = sessionStorage.getItem("access_token");
    const tokenPayload = decode(token);

    this.items = [
      {
        label: 'Opciones',
        icon: 'pi pi-fw pi-pencil',
        visible: this.roleGuardService.validarRol(tokenPayload.authorities, ["Profesor", "Administrador"]),
        items: [
          {
            label: 'Registrar notas', icon: 'pi pi-fw pi-chart-line',
            command: (event) => {
              this.enviarRegistrarNotas()
            }
          },
          {
            label: 'Reporte Estudiante', icon: 'pi pi-fw pi-chart-line',
            command: (event) => {
              this.enviarReporte()
            }
          }
        ]
      },
      {
        label: 'Configuración',
        icon: 'pi pi-fw pi-cog',
        visible: this.roleGuardService.validarRol(tokenPayload.authorities, ["Profesor", "Administrador"]),
        items: [
          {
            label: 'Usuarios', icon: 'pi pi-users',
            command: (event) => {
              this.enviarUsuarios()
            }
          }, {
            label: 'Cursos', icon: 'pi pi-user-plus',
            command: (event) => {
              this.enviarCursos()
            },
          },
          {
            label: 'Asignar estudiantes', icon: 'pi pi-user-plus',
            command: (event) => {
              this.enviarCursoEstudiantes()
            },
          },
          {
            label: 'Actividades', icon: 'pi pi-user-plus',
            command: (event) => {
              this.enviarActividades()
            },
          }

        ]
      },
      {
        label: 'Portal',
        icon: 'pi pi-fw pi-pencil',
        visible: this.roleGuardService.validarRol(tokenPayload.authorities, ["Estudiante"]),
        items: [
          {
            label: 'Ver notas', icon: 'pi pi-fw pi-chart-line',
            command: (event) => {
              this.notasEstudiante()
            }
          }
        ]
      },
    ];
  }

  ngAfterViewInit() {
  }

  enviarUsuarios() {
    this.router.navigate(['usuarios'])
  }

  enviarCursos() {
    this.router.navigate(['cursos'])
  }

  enviarCursoEstudiantes() {
    this.router.navigate(['cursoEstudiante'])
  }

  cerrarSesion() {
    this.router.navigate(['login'])
  }


  enviarActividades() {
    this.router.navigate(['actividades'])
  }

  enviarRegistrarNotas() {
    this.router.navigate(['registrarNotas'])
  }

  notasEstudiante() {
    this.router.navigate(['notasEstudiante'])
  }

  enviarReporte() {
    this.router.navigate(['reporteEstudiante'])
  }
}