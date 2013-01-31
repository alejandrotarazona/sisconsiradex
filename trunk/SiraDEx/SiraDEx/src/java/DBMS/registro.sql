
/*No se como cargar un registro que no nos da;e la aplicaci[on este fue un intento fallido
-- Tipo Actividad
INSERT INTO tipo_actividad VALUES (NEXTVAL('tipo_actividad_seq'),'Responsable en la ejecucion de proyectos del BDPEX', 8, 'Se explican datos sobre un proyecto del BDPEX' , null);
INSERT INTO tipo_actividad VALUES (NEXTVAL('tipo_actividad_seq'),'Diseño y propuesta de nuevos Diplomados', 3, 'Se diseña y propone nuevos diplomados', null);
-- Campos

INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Fecha de inicio', 'fecha',10,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Fecha de fin', 'fecha',10,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Nombre del autor', 'texto',32,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Código', 'texto',15,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Área de atención', 'texto',64,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Comunidad', 'texto',40,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Zona', 'texto',40,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),1,'Horas dedicadas', 'numero',4,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),2,'Fecha', 'fecha',10,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),2,'Autor', 'texto', 25,true);
INSERT INTO campo values(NEXTVAL('campo_seq'),2,'Archivo','texto',25, true);


-- Actividad
INSERT INTO actividad values(NEXTVAL('actividad_seq'),1,null,null,'Por validar','05-38199');
INSERT INTO actividad values(NEXTVAL('actividad_seq'),2,null,null,'Por validar','05-38199');

--Valor
INSERT INTO valor values(1,1,'20/11/2012');
INSERT INTO valor values(2,1,'20/12/2012');
INSERT INTO valor values(3,1,'Diana Vainberg');
INSERT INTO valor values(4,1,'BDPEX30');
INSERT INTO valor values(5,1,'Gestión');
INSERT INTO valor values(6,1,'Hoyo de la puerta');
INSERT INTO valor values(7,1,'Hoyo de la puera');
INSERT INTO valor values(8,1,'10');

INSERT INTO valor values(9,2,'28/11/2012');
INSERT INTO valor values(10,2,'Jorge Garcia');
INSERT INTO valor values(11,2,'Nombre del archivo.txt');
*/


--Permisos

INSERT INTO permiso (nombre) VALUES ('Estudiante');
INSERT INTO permiso (nombre) VALUES ('Empleado');
INSERT INTO permiso (nombre) VALUES ('Obrero');
INSERT INTO permiso (nombre) VALUES ('Profesor');

--Usuarios
INSERT INTO usuario VALUES ('05-38199', '123456', 1, 'Jorge', 'García', '05-38199@usb.ve', '04141360451');
INSERT INTO usuario VALUES ('07-41618', 'diana', 2, 'Diana', 'Vainberg', '07-41618@usb.ve', null);
INSERT INTO usuario VALUES ('jf', 'jf', 3, 'Jose', 'Fernandez', 'jf@usb.ve', '04125555555');
INSERT INTO usuario VALUES ('kdoming', 'kdoming',4, 'Kenyer', 'Dominguez', 'kdoming@usb.ve', null);
INSERT INTO usuario VALUES ('07-10000', 'pedro', 4, 'Pedro', 'Perez', 'pedroperez@usb.ve', null);

--Catalogo
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Coordinaciones', 2);
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Programas', 1);
--INSERT INTO catalogo (nombre, nro_campos) VALUES ('Estudiantes', 1);
--INSERT INTO catalogo (nombre, nro_campos) VALUES ('Carreras', 1);
--INSERT INTO catalogo (nombre, nro_campos) VALUES ('Departamentos', 1);
--INSERT INTO catalogo (nombre, nro_campos) VALUES ('Profesores', 1);

--Campos de coordinaciones
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) VALUES (1, 'Nombre', 'texto');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) VALUES (1, 'Id coordinador', 'texto');

--Campos de programas
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) VALUES (2, 'Nombre', 'texto');

--Campos de estudiante
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (3, 'Nombre estudiante', 'Texto');
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (3, 'carnet', 'Texto');
--      Campos de carreras
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (4, 'Nombre carrera', 'Texto');
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (4, 'Id carrera', 'Texto');
--      Campos de departamentos
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (5, 'Nombre departamento', 'Texto');
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (5, 'Id jefe_dpto', 'Texto');
--      Campos de profesores
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (6, 'Nombre profesor', 'Texto');
--INSERT INTO campo_catalogo (id_catalogo, nombre_campo, tipo_campo) VALUES (6, 'Id profesor', 'Texto');

--Elementos de los catalogos
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (1, 1);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (2, 1);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (3, 2);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (4, 2);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (5, 2);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (6, 2);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (7, 2);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (8, 2);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (9, 2);
INSERT INTO elemento_catalogo (id_elemento, id_catalogo) VALUES (10, 2);


--Valores de los elementos de los catalogos cada valor corresponde a un campo y a un catalogo
--Valores para los nombres de las coordinaciones (asumiendo que 1 es el id campo catalogo de "Nombre de coordinacion"
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (1,1,'Coordinacion de Computacion y Tecnologia de la Informacion');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (1,2,'Coordinacion de Cooperacion Tecnica y Desarrollo Social');
--INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,1,'BPDex');
--INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,1,'Funindes USB');
--INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,1,'Coordinacion de Educacion Permanente');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (2,1,'asoraya');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (2,2,'kdoming');
--INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (2,, 'mpepe');
--INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (2,, 'mmijares');

--	Valores de los nombre de los programas asumiendo que 3 es el codigo de "Nombre de Programa"
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,3,'Educacion Permanente');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,4,'Desarrollo Comunitario');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,5,'Asistencia tecnica a los Sectores Productivos y de Servicios Publicos y Privados');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,6,'Fomento y la Proyeccion Artistica, Cultural y Deportiva');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,7,'Divulgacion y Promocion del quehacer universitario');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,8,'Nivelacion Academica Preuniversitaria Igualdad de oportunidades');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,9,'Emprendimiento y Seguimiento de Egresados');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (3,10,'Formacion Complementaria');


