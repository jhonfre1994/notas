import { usuarioDTO } from './usuarioDTO';
import { RolDTO } from './RolDTO';

export class UsuarioSaveDTO {
    public usuario: usuarioDTO = new usuarioDTO();
    public roles: Array<RolDTO> = [];
} 