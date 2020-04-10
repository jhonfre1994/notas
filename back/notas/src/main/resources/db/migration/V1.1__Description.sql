/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  UTP
 * Created: 10/04/2020
 */

CREATE TABLE USR_ROL (
  id_rol serial,
  nombre varchar(255) DEFAULT NULL,
  PRIMARY KEY (id_rol)
);


CREATE TABLE usr_usuario_rol (
id serial,
  user_id int NOT NULL,
  id_rol int NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES USR_USUARIO (id_usuario),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (id_rol) REFERENCES USR_ROL (id_rol)
);

