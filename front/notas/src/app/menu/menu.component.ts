import { Component, OnInit, AfterViewInit, Version } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd, ChildActivationStart } from '@angular/router';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { SelectItem, MenuItem } from 'primeng/api';

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

  constructor(public router: Router, private _route: ActivatedRoute) {
  }

  ngOnInit() {
    this.items = [
      {
        label: 'Opciones',
        icon: 'pi pi-fw pi-pencil',
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
        /*  visible: this.responsable.rol === 'Usuario' ? false : true, */
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
      }
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

  enviarReporte() {
    this.router.navigate(['reporteEstudiante'])
  }
}