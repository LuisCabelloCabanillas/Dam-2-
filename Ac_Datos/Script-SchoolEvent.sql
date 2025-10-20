create or replace database SchoolEvent;
use SchoolEvent;

create or replace table Usuario(
id int auto_increment primary key,
nombre varchar(20),
apellido varchar(60),
contrasena varchar(100),
Fecha_de_nacimiento date,
correo varchar(50)
);

create or replace table Eventos(
id int auto_increment primary key,
nombre varchar(20),
Lugar varchar(60),
Requisitos varchar(150),
Fecha date,
Consiste varchar(50)
);

create or replace table Recor_Noti(
id int auto_increment primary key,
nombre varchar(20),
Lugar varchar(60),
Fecha date,
Hora TIME
);

create or replace table Inscripcion(
id int auto_increment primary key,
id_evento int not null,
id_usuario int not null,
constraint fk_inscripcion_usuario
foreign key (id_usuario) references Usuario(id),
constraint fk_inscripcion_eventos
foreign key (id_evento) references Eventos(id)
);

create or replace table Pago(
id int auto_increment primary key,
fecha date,
Estado int(1),
id_usuario int not null,
id_eventos int not null,
constraint fk_pago_usuario
foreign key (id_usuario) references Usuario(id),
constraint fk_pago_eventos
foreign key (id_eventos) references Eventos(id)
);

create or replace table Tienen(
id_evento int not null,
id_reco_noti int not null,
constraint fk_tiene_recor_noti1
foreign key (id_reco_noti) references Recor_Noti(id),
constraint fk_tiene_eventos
foreign key (id_evento) references Eventos(id)
);

create or replace table Reciben(
id_usuario int not null,
id_reco_noti int not null,
constraint fk_tiene_recor_noti2
foreign key (id_reco_noti) references Recor_Noti(id),
constraint fk_tiene_usuario
foreign key (id_usuario) references Usuario(id)
);

create or replace table Fotos(
id int auto_increment primary key,
Foto blob,
id_eventos int not null,
constraint fk_foto_eventos
foreign key (id_eventos) references Eventos(id)
);

create or replace table Comentarios(
id int auto_increment primary key,
opinion varchar(200),
id_usuario int not null,
constraint fk_Comentario_eventos
foreign key (id_usuario) references Usuario(id)
);