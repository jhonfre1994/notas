import { CursosDTO } from './CursosDTO';

export class NotaActividadDTO{

    public curso : CursosDTO;
    public notas: Array<NotaActividadDTO> ;
    public promedio : number;
}