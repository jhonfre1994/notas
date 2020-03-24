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

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },


  {
    path: '',
    component: MenuComponent,
    canActivate: [AuthService],
    children: [
      { path: 'usuarios', component: UsuariosComponent},
      { path: 'cursos', component: CursosComponent },
      { path: 'cursoEstudiante', component: CursoEstudianteComponent },
      { path: 'actividades', component: ActividadesComponent },
      { path: 'registrarNotas', component: RegistrarNotasComponent },
      { path: 'reporteEstudiante', component: ReporteEstudianteComponent },

    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
