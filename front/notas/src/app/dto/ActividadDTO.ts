import { CursosDTO } from './CursosDTO';
import { MateriaDTO } from './MateriaDTO';

export class ActividadDTO{

    public idActividad : number;
    public nombreActividad : string;
    public idMateria : MateriaDTO;
}