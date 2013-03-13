


--Tipo de actividad

INSERT INTO tipo_actividad (nombre_tipo_actividad,tipo_p_r,nro_campos,descripcion,programa,validador,producto) VALUES ('Formulacion de proyectos del BPDEx', 'P',11,'Projecto del BPDEx','Acción social y desarrollo comunitario','BPDEx','Proyecto del BDPEx');
INSERT INTO tipo_actividad (nombre_tipo_actividad,tipo_p_r,nro_campos,descripcion,programa,validador,producto) VALUES ('Tutoría de PSC', 'P',5,'Nuevos diplomados','Tutoría','PSC','Informe');
INSERT INTO tipo_actividad (nombre_tipo_actividad,tipo_p_r,nro_campos,descripcion,programa,validador,producto) VALUES ('Tutoría de pasantías cortas profesionales', 'P',2,'Tutoria de pasantias','Cooperacion tecnica','CCTDS','Informe de pasantia corta');

--Campo de un tipo de actividad

INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Fecha de postulación', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Fecha de inicio', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Fecha fin', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Nombre del autor', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Título', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Área de atención', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Comunidad', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Zonas', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Número de beneficiaros', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Tipo de beneficiaros', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (1,'Costo', 'numero', 20, true);


INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (2,'Fecha de inscripción', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (2,'Fecha de culminación', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (2,'Nombre del tutor', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (2,'Nombre del alumno', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (2,'Código', 'texto', 20, true);

INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (3,'Fecha de inscripción', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) VALUES (3,'Fecha de culminación', 'fecha', 20, true);
--Actividad

--INSERT INTO actividad (id_actividad, id_tipo_actividad, validacion, creador, fecha_creacion, modificador, fecha_modif) VALUES (15,2,null,'admin','10/01/13',null,null);

--Valor
/*
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (12,15,'10/01/13');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (13,15,'20/01/13');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (14,15,'Kenyer Dominguez');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (15,15,'Diana Vainberg');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (16,15,'10A');
*/
--Permisos

INSERT INTO permiso (nombre) VALUES ('Estudiante');
INSERT INTO permiso (nombre) VALUES ('Empleado');
INSERT INTO permiso (nombre) VALUES ('Obrero');
INSERT INTO permiso (nombre) VALUES ('Profesor');


---Tiene_permiso
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (1,4);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (2,2);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (2,4);


--Usuarios
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) VALUES ('00-00000', '123456', 'obrero', 3, 'Pablo', 'Perez', '04321234567', '00-00000@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) VALUES ('05-38978', 'alejandro', 'empleado', 3, 'Alejandro', 'Tarazona', '04121341842', '05-38978@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) VALUES ('05-38199', '123456', 'WM', 3, 'Jorge', 'García', '04141360451', '05-38199@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) VALUES ('07-41618', 'diana', 'estudiante', 3, 'Diana', 'Vainberg', '04126053552', '07-41618@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) VALUES ('jf', 'jf', 'estudiante', 3, 'Jose', 'Fernandez', '04125555555', 'jf@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) VALUES ('kdoming', 'kdoming', 'profesor',4, 'Kenyer', 'Dominguez', null, 'kdoming@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) VALUES ('07-10000', 'pedro', 'DEx',2, 'PSC', null,  null, 'pedroperez@usb.ve');



--Catalogo
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Coordinaciones', 2);
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Programas', 1);
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Estudiantes', 0);
--INSERT INTO catalogo (nombre, nro_campos) VALUES ('Carreras', 1);
--INSERT INTO catalogo (nombre, nro_campos) VALUES ('Departamentos', 1);
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Profesores', 0);
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Empleados' , 0);
INSERT INTO catalogo (nombre, nro_campos) VALUES ('Obreros', 0);

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
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);


--Valores de los elementos de los catalogos cada valor corresponde a un campo y a un catalogo
--Valores para los nombres de las coordinaciones (asumiendo que 1 es el id campo catalogo de "Nombre de coordinacion"
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (1,1,'Coordinacion de Igualdad de Oportunidades');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (1,2,'Coordinacion de Cooperacion Tecnica');
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

--      Permisos por Tipo de Actividad  --
