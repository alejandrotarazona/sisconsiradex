
--Permisos

INSERT INTO permiso (nombre) VALUES ('Estudiante');
INSERT INTO permiso (nombre) VALUES ('Empleado');
INSERT INTO permiso (nombre) VALUES ('Obrero');
INSERT INTO permiso (nombre) VALUES ('Profesor');

--Usuarios
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES ('09-00000', 'obrero', 'Pablo', 'Perez', '04321234567', 
'00-00000@usb.ve');
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES ('05-38978', 'WM', 'Alejandro', 'Tarazona', '04121341842',
 'alejandrotarazona@gmail.com');
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES 
('gsalazar', 'empleado', 'Gerson', 'Salazar', '01469060847', 'gsalazar@usb.ve');
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES ('07-41618', 'WM', 'Diana', 'Vainberg', '04126053552', 
'dianak90@gmail.com');
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES ('09-00001', 'estudiante','Jose', 'Fernandez', '04125555555', 'jf@usb.ve');
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES ('05-38199', 'WM','Jorge', 'García', '04141360451', 
'jorgedgb@gmail.com');
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES ('kdoming', 'profesor','Kenyer', 'Dominguez', '', 'kdoming@usb.ve');
INSERT INTO usuario (usbid,rol,nombres,apellidos,telefono,email) 
VALUES ('99-10000', 'Unidad de Participación y Acción Social','Pedro', 
'Gomez',  '', '07-10000@usb.ve');




--Catalogo
INSERT INTO catalogo (nombre) VALUES ('Dependencias');
INSERT INTO catalogo (nombre) VALUES ('Programas');
INSERT INTO catalogo (nombre, participa) VALUES ('Estudiantes', true);
INSERT INTO catalogo (nombre, participa) VALUES ('Profesores', true);
INSERT INTO catalogo (nombre, participa) VALUES ('Empleados Administrativos', true);
INSERT INTO catalogo (nombre, participa) VALUES ('Obreros', true);

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
VALUES (3, 'Nombre', 'usuario');

--      Campos de profesores
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (4, 'USB-ID', 'usbid');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (4, 'Nombre', 'usuario');

--      Campos de empleados
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (5, 'USB-ID', 'usbid');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (5, 'Nombre', 'usuario');

--      Campos de obreros
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (6, 'USB-ID', 'usbid');
INSERT INTO campo_catalogo (id_cat, nombre_campo, tipo_campo) 
VALUES (6, 'Nombre', 'usuario');


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
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (8,22,'gsalazar');
--      Valor del campo Nombre de un empleado
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (9,21,'Pedro Gomez');
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (9,22,'Gerson Salazar');

--      Valor del campo Id de un obrero
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (10,23,'09-00000');
--      Valor del campo Nombre de un obrero
INSERT INTO valor_catalogo (id_campo, id_elemento, valor) VALUES (11,23,'Pablo Perez');

