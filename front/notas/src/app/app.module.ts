
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { MaterialModule } from './material-module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
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
    HttpClientModule
  ],
  providers: [AuthService,
    { provide: LocationStrategy, useClass: HashLocationStrategy},],
  bootstrap: [AppComponent]
})
export class AppModule { }
