CREATE TABLE USR_USUARIO (
    id_usuario serial,
    nombre_usuario VARCHAR(45),
    nombres VARCHAR(45),
    apellidos VARCHAR(45),
    correo VARCHAR(45),
    genero VARCHAR(1),
    contrasena VARCHAR(100),
    rol varchar(20),
  PRIMARY KEY (id_usuario)
);


CREATE TABLE curso (
    id_curso serial,
    nombre_curso VARCHAR(45),
    codigo VARCHAR(45),
    id_profesor int,
    constraint fk_profesor foreign key (id_profesor) references USR_USUARIO(id_usuario),
  PRIMARY KEY (id_curso)
);


CREATE TABLE actividad (
    id_actividad serial,
    nombre_actividad VARCHAR(45),
    id_curso int,
    constraint fk_curso foreign key (id_curso) references curso(id_curso) on delete cascade on update cascade,
  PRIMARY KEY (id_actividad)
);

CREATE TABLE curso_estudiante (
    id serial,
    id_estudiante int,
    id_curso int,
    constraint fk_estudiante foreign key (id_estudiante) references USR_USUARIO(id_usuario) on delete cascade on update cascade,
    constraint fk_curso_estudiante foreign key (id_curso) references curso(id_curso) on delete cascade on update cascade,
  PRIMARY KEY (id)
);

CREATE TABLE nota_actividad (
    id_nota serial,
    id_actividad int,
    id_estudiante int,
    nota int,
    fecha_digitacion date,
    constraint fk_actividad foreign key (id_actividad) references actividad(id_actividad) on delete cascade on update cascade,
    constraint fk_estudiante_nota foreign key (id_estudiante) references USR_USUARIO(id_usuario) on delete cascade on update cascade,
  PRIMARY KEY (id_nota)
);

