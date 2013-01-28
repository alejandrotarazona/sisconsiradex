
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