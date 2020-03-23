import { usuarioDTO } from './usuarioDTO';
import { CursosDTO } from './CursosDTO';

export class SaveCursoEstudiante{
    public estudiantes : Array<usuarioDTO> =[]
    public curso: CursosDTO = new CursosDTO();
}