import { usuarioDTO } from './usuarioDTO';

export class CursosDTO{
    public  idCurso: number;
    public  nombreCurso: string;
    public  codigo : string;
    public  idProfesor : usuarioDTO;
    public responsable: string
}