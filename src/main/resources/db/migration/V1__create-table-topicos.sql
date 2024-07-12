create table topicos(
                        id bigint not null auto_increment,
                        titulo varchar(100) not null unique,
                        mensaje varchar(750) not null unique,
                        fecha_creacion datetime not null,
                        status varchar(50) not null,
                        categoria varchar(50) not null,
                        autor varchar(100) not null,

                        primary key(id)
);