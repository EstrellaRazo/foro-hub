create table usuarios(
                        id bigint not null auto_increment,
                        nombre varchar(100) not null,
                        usuario varchar(50) not null unique,
                        clave varchar(300) not null,

                        primary key(id)
);