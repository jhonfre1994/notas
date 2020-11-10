/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  UTP
 * Created: 12/04/2020
 */

ALTER TABLE MATERIA 
DROP COLUMN profesor;


ALTER TABLE MATERIA 
ADD COLUMN profesor int;

ALTER TABLE MATERIA ADD constraint fk_materiaProfeso foreign key (profesor) references USR_USUARIO(id_usuario);


INSERT INTO usr_usuario(
	id_usuario, nombre_usuario, nombres, apellidos, correo, genero, contrasena, rol)
	VALUES (1, 'yeiner', 'yeiner', 'cardona', 'yei@gmail.com', 'M', '$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u', null);

INSERT INTO public.usr_rol(id_rol, nombre) VALUES (1, 'Administrador');
INSERT INTO public.usr_rol(id_rol, nombre) VALUES (2, 'Estudiante');
INSERT INTO public.usr_rol(id_rol, nombre) VALUES (3, 'Profesor');

INSERT INTO public.usr_usuario_rol(id, user_id, id_rol)VALUES (1, 1, 1);
INSERT INTO public.usr_usuario_rol(id, user_id, id_rol)VALUES (2, 1, 3);