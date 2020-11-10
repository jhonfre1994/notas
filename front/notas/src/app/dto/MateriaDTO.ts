import { usuarioDTO } from './usuarioDTO';
import { CursosDTO } from './CursosDTO';

export class MateriaDTO {

    public idMateria: number;
    public nombre: string;
    public abreviatura: string;
    public profesor: usuarioDTO;
    public idCurso: CursosDTO;
    public responsable:string;
}