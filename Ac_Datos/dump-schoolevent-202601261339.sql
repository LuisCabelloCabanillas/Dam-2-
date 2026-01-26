-- ===========================================
-- Dump convertido para PostgreSQL
-- Base de datos: schoolevent
-- ===========================================

-- DROP TABLES si existen
DROP TABLE IF EXISTS comentarios CASCADE;
DROP TABLE IF EXISTS fotos CASCADE;
DROP TABLE IF EXISTS inscripcion CASCADE;
DROP TABLE IF EXISTS pago CASCADE;
DROP TABLE IF EXISTS reciben CASCADE;
DROP TABLE IF EXISTS tienen CASCADE;
DROP TABLE IF EXISTS recor_noti CASCADE;
DROP TABLE IF EXISTS eventos CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;

-- ===========================================
-- Tabla usuario
-- ===========================================
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(60),
    contrasena VARCHAR(100),
    fecha_de_nacimiento DATE,
    correo VARCHAR(50),
    tipo VARCHAR(10) CHECK (tipo IN ('Admin', 'User'))
);

INSERT INTO usuario (id, nombre, apellido, contrasena, fecha_de_nacimiento, correo, tipo) VALUES
(1,'Luis','Cabello','qwe123','2000-05-15','Luis@example.com','User'),
(2,'Jesus','Cabello','qwe123','2000-05-15','Jesus@example.com','User'),
(3,'Pedro','Cabello','qwe123','2000-05-15','Admin@example.com','Admin'),
(4,'Manuel','Cabello','qwe123','2000-05-15','Manuel@example.com','Admin'),
(5,'Sofia','Cabello','qwe123','2000-05-15','Sofia@example.com','User'),
(6,'Sara','Cabello','qwe123','2000-05-15','Sara@example.com','User'),
(7,'Sara','Cabello','qwe123','2000-05-15','Sara@example.com','User'),
(8,'Sara','Cabello','qwe123','2000-05-15','Sara@example.com','User'),
(9,'Luis','Cabello','qwe123','2000-05-15','Luis@example.com','User'),
(10,'Luis','Cabello','qwe123','2000-05-15','Paco@example.com','User');

-- ===========================================
-- Tabla eventos
-- ===========================================
CREATE TABLE eventos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(60),
    lugar VARCHAR(60),
    requisitos VARCHAR(150),
    fecha DATE,
    consiste VARCHAR(200),
    categoria VARCHAR(20) CHECK (categoria IN ('Deportivo','Ciencia','Cultural','otros'))
);

INSERT INTO eventos (id, nombre, lugar, requisitos, fecha, consiste, categoria) VALUES
(5,'Festival de cocina','Aula de Cocina','Ser alumno del centro o tener familia','2026-01-28','Torneo para ver quien hace la mejor paella','otros'),
(8,'Baile escolar','Pabellón','Ser mayor de 14 años o ser de primero de la eso','2025-12-13','Día para hacer torneos deportivos','otros'),
(9,'Olimpiadas Escolares','Cancha Principal','Estar inscrito en el colegio','2025-05-10','Competencias deportivas','Deportivo'),
(13,'Olimpiadas Escolares','Cancha Principal','Estar inscrito en el colegio','2025-05-10','Competencias deportivas','Deportivo'),
(14,'Feria de Ciencia Escolar','Laboratorio de Ciencias','Proyecto aprobado','2025-06-25','Exposición de proyectos científicos','Ciencia'),
(15,'Festival de Talentos','Auditorio del Colegio','Inscripción previa','2025-08-15','Presentación de talentos artísticos','Cultural'),
(16,'Jornada de Limpieza','Patio y áreas comunes','Participación voluntaria','2025-03-12','Actividad de limpieza general','otros');

-- ===========================================
-- Tabla comentarios
-- ===========================================
CREATE TABLE comentarios (
    id SERIAL PRIMARY KEY,
    opinion VARCHAR(200),
    id_usuario INT NOT NULL REFERENCES usuario(id)
);

INSERT INTO comentarios (id, opinion, id_usuario) VALUES
(1,'Evento muy dinámico y fácil de entender',1),
(2,'Evento muy dinámico y fácil de entender',1),
(3,'Evento muy dinámico y fácil de entender',1),
(4,'Evento muy dinámico y fácil de entender',2),
(5,'Evento muy dinámico y fácil de entender',4),
(6,'Evento muy dinámico y fácil de entender',1),
(7,'Evento muy dinámico y fácil de entender',2),
(8,'Evento muy dinámico y fácil de entender',4),
(9,'Evento muy dinámico y fácil de entender',3),
(11,'Evento muy dinámico y fácil de entender',6),
(12,'Evento muy dinámico y fácil de entender',1);

-- ===========================================
-- Tabla fotos
-- ===========================================
CREATE TABLE fotos (
    id SERIAL PRIMARY KEY,
    foto VARCHAR(255),
    id_eventos INT NOT NULL REFERENCES eventos(id)
);

-- ===========================================
-- Tabla inscripcion
-- ===========================================
CREATE TABLE inscripcion (
    id SERIAL PRIMARY KEY,
    id_evento INT NOT NULL REFERENCES eventos(id),
    id_usuario INT NOT NULL REFERENCES usuario(id)
);

-- ===========================================
-- Tabla pago
-- ===========================================
CREATE TABLE pago (
    id SERIAL PRIMARY KEY,
    fecha DATE,
    estado INT CHECK (estado IN (0,1)),
    id_usuario INT NOT NULL REFERENCES usuario(id),
    id_eventos INT NOT NULL REFERENCES eventos(id)
);

-- ===========================================
-- Tabla recor_noti
-- ===========================================
CREATE TABLE recor_noti (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(20),
    lugar VARCHAR(60),
    fecha DATE,
    hora TIME
);

-- ===========================================
-- Tabla reciben
-- ===========================================
CREATE TABLE reciben (
    id_usuario INT NOT NULL REFERENCES usuario(id),
    id_reco_noti INT NOT NULL REFERENCES recor_noti(id)
);

-- ===========================================
-- Tabla tienen
-- ===========================================
CREATE TABLE tienen (
    id_evento INT NOT NULL REFERENCES eventos(id),
    id_reco_noti INT NOT NULL REFERENCES recor_noti(id)
);
