create or replace database SchoolEvent;
use SchoolEvent;

create or replace table Usuario(
id int auto_increment primary key,
nombre varchar(50),
apellido varchar(60),
contrasena varchar(100),
fecha_de_nacimiento date,
correo varchar(50)
);

create or replace table Eventos(
id int auto_increment primary key,
nombre varchar(60),
lugar varchar(60),
requisitos varchar(150),
fecha date,
consiste varchar(200)
);

create or replace table Recor_Noti(
id int auto_increment primary key,
nombre varchar(20),
lugar varchar(60),
fecha date,
hora TIME
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
estado int(1),
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
foto blob,
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


ALTER TABLE Usuario
ADD COLUMN tipo ENUM('Admin', 'User');

ALTER TABLE eventos 
ADD COLUMN categoria ENUM('Deportivo', 'Ciencia', 'Cultural', 'otros');

ALTER TABLE Fotos
MODIFY foto VARCHAR(255);


SELECT * 
FROM Usuario;

SELECT * 
FROM eventos;

SELECT * 
FROM inscripcion;

SELECT * 
FROM fotos;

SELECT * 
FROM comentarios;

INSERT INTO Eventos (nombre, lugar, requisitos, fecha, consiste, categoria)
VALUES
('Olimpiadas Escolares', 'Cancha Principal', 'Estar inscrito en el colegio', '2025-05-10', 'Competencias deportivas', 'Deportivo');

INSERT INTO Eventos (nombre, lugar, requisitos, fecha, consiste, categoria)
VALUES
('Feria de Ciencia Escolar', 'Laboratorio de Ciencias', 'Proyecto aprobado', '2025-06-25', 'Exposición de proyectos científicos', 'Ciencia');

INSERT INTO Eventos (nombre, lugar, requisitos, fecha, consiste, categoria)
VALUES
('Festival de Talentos', 'Auditorio del Colegio', 'Inscripción previa', '2025-08-15', 'Presentación de talentos artísticos', 'Cultural');

INSERT INTO Eventos (nombre, lugar, requisitos, fecha, consiste, categoria)
VALUES
('Jornada de Limpieza', 'Patio y áreas comunes', 'Participación voluntaria', '2025-03-12', 'Actividad de limpieza general', 'otros');


INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',1);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',1);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',1);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',2);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',4);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',1);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',2);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',4);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',3);

INSERT INTO comentarios (opinion, id_usuario)
VALUES
('Evento muy dinámico y fácil de entender',6);

show tables;

describe eventos;

describe fotos;

select *
from eventos e
where e.fecha = '2025-05-10'
and e.categoria = 'Deportivo';

select e.id as id_evento, e.nombre, e.lugar, e.requisitos, e.fecha, e.consiste, e.categoria, COUNT(i.id) as total_asistente
from eventos e
left join inscripcion i on i.id_evento = e.id
group by e.id, e.nombre, e.lugar, e.requisitos, e.fecha, e.consiste, e.categoria
order by total_asistente desc
limit 5;

select u.id, u.nombre, COUNT(distinct c.id) as suma_de_comentarios, COUNT(distinct i.id) as suma_de_inscripciones, (COUNT(distinct c.id) + Count(distinct i.id)) as suma_global
from usuario u 
left join comentarios c on c.id_usuario = u.id
left join inscripcion i on i.id_usuario = u.id
group by u.id, u.nombre
order by suma_global desc
limit 1;



