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