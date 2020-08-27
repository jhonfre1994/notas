/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jhonfre
 * Created: 27/08/2020
 */

insert into USR_USUARIO(id_usuario, nombre_usuario, nombres, 
apellidos, correo, genero, contrasena, rol) values 
(1, 'yeiner', 'yeiner leandro', 'cardona', 'yei@utp.edu.co', 'M', '$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u', '');

INSERT INTO USR_ROL (id_rol, nombre) VALUES (1, 'Administrativo');
INSERT INTO USR_ROL (id_rol, nombre) VALUES (2, 'Profesor');
INSERT INTO USR_ROL (id_rol, nombre) VALUES (3, 'Estudiante');


INSERT INTO usr_usuario_rol(id, user_id, id_rol) VALUES(1,1,1);
INSERT INTO usr_usuario_rol(id, user_id, id_rol) VALUES(2,1,2);
