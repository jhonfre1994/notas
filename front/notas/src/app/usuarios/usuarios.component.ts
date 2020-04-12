import { Component, OnInit } from '@angular/core';
import { UsuariosService } from './usuarios.service';
import { usuarioDTO } from '../dto/usuarioDTO';
import { SelectItem, MessageService, ConfirmationService } from "primeng/api";
import { UsuarioSaveDTO } from '../dto/UsuarioSaveDTO';
import { RolDTO } from '../dto/RolDTO';

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
  public rolesList: Array<RolDTO> = new Array<RolDTO>();
  public rolesListModel: Array<RolDTO> = new Array<RolDTO>();
  public guardarUsuario: UsuarioSaveDTO = new UsuarioSaveDTO();
  public guardarUsuarioForm: usuarioDTO = new usuarioDTO();
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
    this.listarRoles()
  }

  confirmar(usuario: usuarioDTO) {
    this.confirmationService.confirm({
      message: 'Esta seguro que desea eliminar el usuario?',
      accept: () => {
        this.eliminarUsuario(usuario)
      }
    });
  }

  listarTodos() {
    this.usuariosService.listarUsuarios().subscribe(res => {
      if (res != null) {
        this.usuariosList = res;
      }
    },
      error => {
        this.showToast("error", "", error.error.message);
      })
  }

  listarRoles() {
    this.usuariosService.listarRoles().subscribe(res => {
      if (res != null) {
        this.rolesList = res
      }
    })
  }

  public showToast(tipo: string, resumen: string, detalle: string): void {
    this.messageService.add({ severity: tipo, summary: resumen, detail: detalle })
  }


  public comprobarParametros(): boolean {
    if (this.guardarUsuario.usuario.nombres == null) {
      this.showToast("error", "Error de Atributos", "El nombre no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.usuario.apellidos == null) {
      this.showToast("error", "Error de Atributos", "El apellido no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.usuario.nombreUsuario == null) {
      this.showToast("error", "Error de Atributos", "El nombre usuario no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.usuario.contrasena == null) {
      this.showToast("error", "Error de Atributos", "La Contraseña no puede estar vació");
      return false;
    }
    /* if (this.guardarUsuario.usuariorol == null) {
      this.showToast("error", "Error de Atributos", "El rol no puede estar vació");
      return false;
    } */
    if (this.guardarUsuario.usuario.correo == null) {
      this.showToast("error", "Error de Atributos", "El correo no puede estar vació");
      return false;
    }
    if (this.guardarUsuario.usuario.genero == null) {
      this.showToast("error", "Error de Atributos", "El genero no puede estar vació");
      return false;
    }
    return true;
  }


  guardar() {
    this.guardarUsuario.usuario.genero = this.selectGenero.genero
    //this.guardarUsuario.rol = this.selectRol.genero
    this.guardarUsuario.roles = this.rolesListModel
    if (this.guardarUsuario.usuario.idUsuario != 0) {
      this.guardarUsuario.usuario.idUsuario = this.guardarUsuario.usuario.idUsuario
    } else {
      this.guardarUsuario.usuario.idUsuario = 0
    }

    if (this.comprobarParametros() == true) {
      this.usuariosService.guardarUsuario(this.guardarUsuario).subscribe(res => {
        if (res != null) {
          this.display = false;
          this.listarTodos()
          /* this.guardarUsuario = res */
          this.showToast("success", "Bien", "Usuario guardado correctamente");
        }
        delete this.guardarUsuario
        this.guardarUsuario = new UsuarioSaveDTO();
      })
    }
  }

  editar(usu: usuarioDTO) {
    this.rolesListModel = []
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
    usu.roles.map((item) => this.rolesListModel.push(item));
    usu.contrasena = ''
    this.guardarUsuario.usuario = usu
    this.display = true
  }

  limpiarCampos() {
    delete this.guardarUsuario
    this.guardarUsuario = new UsuarioSaveDTO
    delete this.selectGenero
    delete this.selectRol
    this.selectRol = new DropDown
    this.selectGenero = new DropDown
    this.rolesListModel = [];
  }

  showDialog() {
    this.limpiarCampos()
    this.display = true;
  }

  eliminarUsuario(usu: usuarioDTO) {
    this.usuariosService.eliminarUsuario(usu.idUsuario).subscribe(res => {
      if (res != null) {
        this.showToast("success", "Bien", "Usuario eliminado correctamente");
        this.listarTodos()
      }
    })
  }
}
