import { Component, OnInit } from '@angular/core';
import { UsuariosService } from './usuarios.service';
import { usuarioDTO } from '../dto/usuarioDTO';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";

export class DropDown {
  public genero: string
}

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
  providers: [UsuariosService, MessageService, ConfirmationService]
})
export class UsuariosComponent implements OnInit {
  display: boolean = false;
  public usuariosList: Array<usuarioDTO> = new Array<usuarioDTO>();
  public guardarUsuario: usuarioDTO = new usuarioDTO();
  selectGenero: DropDown = new DropDown()
  generos: Array<DropDown> = new Array<DropDown>();

  selectRol: DropDown = new DropDown()
  roles: Array<DropDown> = new Array<DropDown>();

  constructor(private usuariosService: UsuariosService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) {

    this.generos = [
      { genero: 'F' },
      { genero: 'M' }
    ]

    this.roles = [
      { genero: 'Profesor' },
      { genero: 'Estudiante' }
    ]

  }

  ngOnInit() {
    this.listarTodos()
  }

  confirmar(usuario: usuarioDTO) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to perform this action?',
      accept: () => {
        this.eliminarUsuario(usuario)
      }
    });
  }

  listarTodos() {
    this.usuariosService.listarUsuarios().subscribe(res => {
      if (res != null) {
        console.log(res)
        this.usuariosList = res;
      }
    })
  }

  public showToast(tipo: string, resumen: string, detalle: string): void {
    this.messageService.add({ severity: tipo, summary: resumen, detail: detalle })
  }


  public comprobarParametros(): boolean {
    if (this.guardarUsuario.nombres == null) {
      this.showToast("error", "Error de Atributos", "El nombre no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.apellidos == null) {
      this.showToast("error", "Error de Atributos", "El apellido no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.nombreUsuario == null) {
      this.showToast("error", "Error de Atributos", "El nombre usuario no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.contrasena == null) {
      this.showToast("error", "Error de Atributos", "La Contraseña no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.rol == null) {
      this.showToast("error", "Error de Atributos", "El rol no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.correo == null) {
      this.showToast("error", "Error de Atributos", "El correo no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.genero == null) {
      this.showToast("error", "Error de Atributos", "El genero no puede estar vació");
      return false;
    }
    return true;
  }


  guardar() {
    this.guardarUsuario.genero = this.selectGenero.genero
    this.guardarUsuario.rol = this.selectRol.genero
    if (this.guardarUsuario.idUsuario != 0) {
      this.guardarUsuario.idUsuario = this.guardarUsuario.idUsuario
    } else {
      this.guardarUsuario.idUsuario = 0
    }

    if (this.comprobarParametros() == true) {
      console.log(this.guardarUsuario)
      this.usuariosService.guardarUsuario(this.guardarUsuario).subscribe(res => {
        if (res != null) {
          this.display = false;
          console.log(res)
          this.listarTodos()
          /* this.guardarUsuario = res */
        }
        delete this.guardarUsuario
        this.guardarUsuario = new usuarioDTO();
      })
    }
  }

  editar(usu: usuarioDTO) {
    console.log(usu)
    this.generos.forEach(element => {
      if (element.genero == usu.genero) {
        this.selectGenero = element
      }
    });

    this.roles.forEach(element => {
      if (element.genero == usu.rol) {
        this.selectRol = element
      }
    });

    this.guardarUsuario = usu
    this.display = true
  }

  limpiarCampos() {
    delete this.guardarUsuario
    this.guardarUsuario = new usuarioDTO
    delete this.selectGenero
    delete this.selectRol
    this.selectRol = new DropDown
    this.selectGenero = new DropDown

  }

  showDialog() {
    this.limpiarCampos()
    this.display = true;
  }

  eliminarUsuario(usu: usuarioDTO) {
    this.usuariosService.eliminarUsuario(usu.idUsuario).subscribe(res => {
      if (res != null) {
        console.log(res)
        this.listarTodos()
      }
    })
  }
}
