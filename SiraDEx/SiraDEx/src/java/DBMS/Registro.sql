
--Tipo de actividad

INSERT INTO tipo_actividad (nombre_tipo_actividad,tipo_p_r,descripcion,
programa,validador) VALUES ('Formulacion de proyectos del BPDEx', 'R',
'Proyecto del BPDEx','Acción social y desarrollo comunitario',
'Unidad de Participación y Acción Social');
--'Proyecto del BDPEx');*/
INSERT INTO tipo_actividad (nombre_tipo_actividad,tipo_p_r,descripcion,
programa,validador) VALUES ('Tutoría de Proyecto de Servicio Comunitario',
'P','Tutoría de proyectos de servicio comunitario','Acción Social y Desarrollo Comunitario',
'Coordinación de Formación Complementaria General');
--'Informe de Servicio Comunitario del estudiante y acta de evaluación');*/
INSERT INTO tipo_actividad (nombre_tipo_actividad,tipo_p_r,descripcion,
programa,validador) VALUES ('Tutoría de pasantías cortas profesionales',
'R','Tutoria de pasantías cortas','Cooperación técnica',
'Coordinación de Cooperación Técnica');
--'Informe de pasantia corta del estudiante y acta de evaluación');
INSERT INTO tipo_actividad (nombre_tipo_actividad,tipo_p_r,descripcion,
programa,validador) VALUES ('Participación en actividades de voluntariado en representación de la USB',
'R','Actividades de voluntariado representando la USB', 'Acción Social y Desarrollo Comunitario',
'Unidad de Participación y Acción Social');
--'Informe de participación'

--Campo de un tipo de actividad

INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio)
VALUES (1,'Fecha de postulación', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio)
VALUES (1,'Fecha de inicio', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Fecha fin', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Nombre del autor', 'texto', 30, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Título', 'texto', 40, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Área de atención', 'texto', 40, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Comunidad', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Zonas', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Número de beneficiaros', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Tipo de beneficiaros', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Costo', 'numero', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (1,'Proyecto del BPDEX','producto',0,true);

INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (2,'Fecha de inscripción', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (2,'Fecha de culminación', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (2,'Nombre del tutor', 'texto', 30, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (2,'Nombre del alumno', 'texto', 30, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (2,'Código', 'texto', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (2,'Informe de Servicio Comunitario del estudiante','producto',0,true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (2,'Acta de evaluación','producto',0,true);

INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (3,'Fecha de inscripción', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (3,'Fecha de culminación', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (3,'Informe de pasantía corta','producto',0, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (3,'Acta de evaluación','producto',0, true);

INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (4,'Fecha de inicio', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (4,'Fecha fin', 'fecha', 20, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (4,'Comunidad', 'texto', 40, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (4,'Nombre del voluntario', 'texto', 30, true);
INSERT INTO campo (id_tipo_actividad, nombre_campo,tipo_campo,longitud,obligatorio) 
VALUES (4,'Informe de participación','producto',0, true);
--Actividad

INSERT INTO actividad (id_actividad, id_tipo_actividad, validacion, creador, 
fecha_creacion, modificador, fecha_modif) VALUES (15,2,'','admin','10/01/13','','');

--Valor

INSERT INTO valor (id_campo, id_actividad, valor) VALUES (12,15,'10/01/13');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (13,15,'20/01/13');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (14,15,'Kenyer Dominguez');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (15,15,'Diana Vainberg');
INSERT INTO valor (id_campo, id_actividad, valor) VALUES (16,15,'10A');

--Permisos

INSERT INTO permiso (nombre) VALUES ('Estudiante');
INSERT INTO permiso (nombre) VALUES ('Empleado');
INSERT INTO permiso (nombre) VALUES ('Obrero');
INSERT INTO permiso (nombre) VALUES ('Profesor');


---Tiene_permiso
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (1,4);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (2,4);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (3,4);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (4,1);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (4,2);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (4,3);
INSERT INTO tiene_permiso (id_tipo_actividad, id_permiso) VALUES (4,4);

--Usuarios
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES ('00-00000', '123456', 'obrero', 3, 'Pablo', 'Perez', '04321234567', 
'00-00000@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES ('05-38978', 'alejandro', 'empleado', 1, 'Alejandro', 'Tarazona', '04121341842',
 'alejandrotarazona@gmail.com');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES 
('webmaster', 'webmaster', 'WM', 2, 'Gerson', 'Salazar', '01469060847', 'gsalazar@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES ('07-41618', 'diana', 'estudiante', 1, 'Diana', 'Vainberg', '04126053552', 
'dianak90@gmail.com');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES ('jf', 'jf', 'estudiante', 1, 'Jose', 'Fernandez', '04125555555', 'jf@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES ('05-38199', '123456', 'estudiante', 2, 'Jorge', 'García', '04141360451', 
'jorgedgb@gmail.com');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES ('kdoming', 'kdoming', 'profesor',4, 'Kenyer', 'Dominguez', '', 'kdoming@usb.ve');
INSERT INTO usuario (usbid,password,rol,tipo,nombres,apellidos,telefono,email) 
VALUES ('99-10000', 'pedrog', 'Unidad de Participación y Acción Social',2, 'Pedro', 
'Gomez',  '', '07-10000@usb.ve');



--Catalogo
INSERT INTO catalogo (nombre) VALUES ('Dependencias');
INSERT INTO catalogo (nombre) VALUES ('Programas');
INSERT INTO catalogo (nombre, participa) VALUES ('Estudiantes', true);
INSERT INTO catalogo (nombre, participa) VALUES ('Profesores', true);
INSERT INTO catalogo (nombre, participa) VALUES ('Empleados', true);
INSERT INTO catalogo (nombre, participa) VALUES ('Obreros', true);
--INSERT INTO catalogo (nombre) VALUES ('Carreras');
--INSERT INTO catalogo (nombre) VALUES ('Departamentos');

--      Campos de dependencias
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (1, 'Nombre', 'texto');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (1, 'Id coordinador', 'texto');

--      Campos de programas
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (2, 'Nombre', 'texto');

--      Campos de estudiantes
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (3, 'USB-ID', 'usbid');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (3, 'Nombre y Apellido', 'texto');

--      Campos de profesores
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (4, 'USB-ID', 'usbid');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (4, 'Nombre y Apellido', 'texto');

--      Campos de empleados
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (5, 'USB-ID', 'usbid');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (5, 'Nombre y Apellido', 'texto');

--      Campos de obreros
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (6, 'USB-ID', 'usbid');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (6, 'Nombre y Apellido', 'texto');


--Elementos de los catalogos
--  Catálogo de Dependencias
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (1);

--  Catálogo de Programas
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (2);

--  Catálogo de Estudiantes
INSERT INTO elemento_catalogo (id_catalogo) VALUES (3);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (3);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (3);


--  Catálogo de Profesores
INSERT INTO elemento_catalogo (id_catalogo) VALUES (4);

--  Catálogo de Empleados
INSERT INTO elemento_catalogo (id_catalogo) VALUES (5);
INSERT INTO elemento_catalogo (id_catalogo) VALUES (5);

--  Catálogo de Obreros
INSERT INTO elemento_catalogo (id_catalogo) VALUES (6);


--Valores de los elementos de los catalogos cada valor corresponde a un campo y a un catalogo
--Valores para los nombres de las Dependencias del DEX (asumiendo que 1 es el id campo catalogo de "Nombre de dependencia"
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,1,'Unidad de Comunicación, Imagen y Divulgación');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,2,'Unidad de Apoyo Administrativo');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,3,'Unidad de Participación y Acción Social');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,4,'Coordinación de Educacion Permanente');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,5,'Coordinación de Cooperación Técnica');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,6,'Coordinación de Igualdad de Oportunidades');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,7,'Coordinación de Formación Complementaria General');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,8,'Coordinación de Extensión de la sede del Litoral');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (1,9,'Coordinación de Emprendimiento y Seguimiento al Egresado');


--      Valores de los emails(porque no sabemos los id) de los coordinadores o jefes de las dep del DEx
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,1,'mavivas@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,2,'bjlugo@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,3,'econes@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,4,'vurbina@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,5,'kdoming@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,6,'eabdala@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,7,'grincon@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,8,'mclaudeville@usb.ve');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (2,9,'vdimperio@usb.ve');

--	Valores de los nombre de los programas asumiendo que 3 es el codigo de "Nombre de Programa"
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (3,10,'Educacion Permanente');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (3,11,'Acción Social y Desarrollo Comunitario');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (3,12,'Cooperación Técnica');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (3,13,'El fomento y la proyección artística, cultural y deportiva');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (3,14,'Divulgación y promoción del quehacer universitario');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (3,15,'Igualdad de Oportunidades');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) 
VALUES (3,16,'Emprendimiento y Seguimiento de Egresados');



--      Valores de los carnets de los estudiantes

INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (4,17,'07-41618');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (4,18,'05-38199');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (4,19,'05-38978');

--      Valores de los nombres de estudiantes
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (5,17,'Diana Vainberg');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (5,18,'Jorge García');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (5,19,'Alejandro Tarazona');


--      Valor del campo Id de un profesor
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (6,20,'kdoming');
--      Valor del campo Nombre de un profesor
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (7,20,'Kenyer Dominguez');

--      Valor del campo Id de un empleado
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (8,21,'99-10000');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (8,22,'webmaster');
--      Valor del campo Nombre de un empleado
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (9,21,'Pedro Gomez');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (9,22,'Gerson Salazar');

--      Valor del campo Id de un obrero
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (10,23,'00-00000');
--      Valor del campo Nombre de un obrero
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (11,23,'Pablo Perez');

