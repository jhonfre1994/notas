import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { CursosComponent } from './cursos/cursos.component';
import { CursoEstudianteComponent } from './curso-estudiante/curso-estudiante.component';
import { ActividadesComponent } from './actividades/actividades.component';
import { RegistrarNotasComponent } from './registrar-notas/registrar-notas.component';
import { AuthService } from './guard/auth.service';
import { ReporteEstudianteComponent } from './reporte-estudiante/reporte-estudiante.component';
import { NotaEstudianteComponent } from './nota-estudiante/nota-estudiante.component';
import { RoleGuardService } from './guard/role-guard.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },


  {
    path: '',
    component: MenuComponent,/* 
    canActivate: [AuthService], */
    children: [
      {
        path: 'usuarios', component: UsuariosComponent,
        canActivate: [RoleGuardService],
        data: {
          expectedRole: ["Profesor", "Administrativo"]
        }
      },
      {
        path: 'cursos', component: CursosComponent,
        canActivate: [RoleGuardService],
        data: {
          expectedRole: ["Profesor", "Administrativo"]
        }
      },
      {
        path: 'cursoEstudiante', component: CursoEstudianteComponent,
        canActivate: [RoleGuardService],
        data: {
          expectedRole: ["Profesor", "Administrativo"]
        }
      },
      {
        path: 'actividades', component: ActividadesComponent,
        canActivate: [RoleGuardService],
        data: {
          expectedRole: ["Profesor", "Administrativo"]
        }
      },
      {
        path: 'registrarNotas', component: RegistrarNotasComponent,
        canActivate: [RoleGuardService],
        data: {
          expectedRole: ["Profesor", "Administrativo"]
        }
      },
      {
        path: 'reporteEstudiante', component: ReporteEstudianteComponent,
        canActivate: [RoleGuardService],
        data: {
          expectedRole: ["Profesor", "Administrativo"]
        }
      },
      {
        path: 'notasEstudiante', component: NotaEstudianteComponent,
        canActivate: [RoleGuardService],
        data: {
          expectedRole: ["Estudiante"]
        }
      },



    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
