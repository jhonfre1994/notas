/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  UTP
 * Created: 12/04/2020
 */

CREATE TABLE MATERIA (
  id_materia serial,
  nombre varchar(255) DEFAULT NULL,  
  abreviatura varchar(255) DEFAULT NULL,
  profesor varchar(255) DEFAULT NULL,
  id_curso int,
  constraint fk_cursoMateria foreign key (id_curso) references curso(id_curso) on delete cascade on update cascade,
  PRIMARY KEY (id_materia)
);


ALTER TABLE actividad 
DROP COLUMN id_curso;

ALTER TABLE actividad 
ADD COLUMN id_materia int;

ALTER TABLE curso 
ADD COLUMN jornada varchar(50);

ALTER TABLE actividad ADD constraint fk_actividadMateria foreign key (id_materia) references MATERIA(id_materia) on delete cascade on update cascade;


