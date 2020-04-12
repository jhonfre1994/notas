
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { MaterialModule } from './material-module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { CursosComponent } from './cursos/cursos.component';
import { CursoEstudianteComponent } from './curso-estudiante/curso-estudiante.component';
import { ActividadesComponent } from './actividades/actividades.component';
import { RegistrarNotasComponent } from './registrar-notas/registrar-notas.component';
import { AuthService } from './guard/auth.service';
import { ReporteEstudianteComponent } from './reporte-estudiante/reporte-estudiante.component';
import { LocationStrategy, PathLocationStrategy, HashLocationStrategy } from '@angular/common';
import { NotaEstudianteComponent } from './nota-estudiante/nota-estudiante.component';
import { HttpModule } from '@angular/http';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { RoleGuardService } from './guard/role-guard.service';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { tokenNotExpired } from 'angular2-jwt';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    UsuariosComponent,
    CursosComponent,
    CursoEstudianteComponent,
    ActividadesComponent,
    RegistrarNotasComponent,
    ReporteEstudianteComponent,
    NotaEstudianteComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    HttpClientModule,
    HttpModule
  ],
  providers: [AuthService,
    RoleGuardService,
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    { provide: 'tokenNotExpired', useValue: tokenNotExpired }, 



  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
