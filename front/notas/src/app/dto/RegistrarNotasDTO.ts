import { ActividadDTO } from './ActividadDTO';
import { usuarioDTO } from './usuarioDTO';

export class RegistrarNotasDTO{
    public idNota : number;
    public nota : number;
    public fechaDigitacion: Date;
    public idActividad: ActividadDTO;
    public idEstudiante : usuarioDTO;
}