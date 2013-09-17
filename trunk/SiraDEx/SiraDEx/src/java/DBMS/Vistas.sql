DROP VIEW elementos CASCADE;
DROP VIEW estudiantes CASCADE;
DROP VIEW obreros CASCADE;
DROP VIEW empleados CASCADE;
DROP VIEW profesores CASCADE;
DROP VIEW permisos CASCADE;
DROP VIEW tipo_p CASCADE;
DROP VIEW tipo_r CASCADE;
DROP VIEW tipo_act__act CASCADE;
DROP VIEW act_participa CASCADE;

CREATE OR REPLACE VIEW elementos
                (id_catalogo, catalogo, id_campo, 
                    nombre_campo, tipo, id_elemento,valor) 
    AS
    SELECT id_catalogo, 
            nombre, 
            id_campo, 
            nombre_campo, 
            tipo_campo, 
            id_elemento, 
            valor 
    FROM valor_catalogo 
        natural join elemento_catalogo 
        natural join catalogo 
        natural join campo_catalogo;

CREATE OR REPLACE VIEW dependencias
                (nombre_campo, tipo, id_elemento,valor)
    AS
    SELECT nombre_campo,
            tipo,
            id_elemento,
            valor
    FROM elementos
    WHERE catalogo = 'Dependencias';

CREATE OR REPLACE VIEW estudiantes
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombres,
            apellidos,
            telefono,
            email
    FROM usuario u
        join catalogo c
    ON     u.tipo = c.id_cat
    WHERE  c.nombre = 'Estudiantes';

CREATE OR REPLACE VIEW obreros
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombres,
            apellidos,
            telefono,
            email
    FROM usuario u
        join catalogo c
    ON     u.tipo = c.id_cat
    WHERE  c.nombre = 'Obrero';

CREATE OR REPLACE VIEW empleados
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombres,
            apellidos,
            telefono,
            email
    FROM usuario u
        join catalogo c
    ON     u.tipo = c.id_cat
    WHERE  c.nombre = 'Empleado';

CREATE OR REPLACE VIEW profesores
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombres,
            apellidos,
            telefono,
            email
    FROM usuario u
        join catalogo c
    ON     u.tipo = c.id_cat
    WHERE  c.nombre = 'Profesores';

CREATE OR REPLACE VIEW programas
    AS
    SELECT *
    FROM elementos
    WHERE  catalogo = 'Programas';

CREATE OR REPLACE VIEW permisos
    AS
    SELECT ta.id_tipo_actividad, p.nombre
    FROM permiso p
        NATURAL JOIN    tiene_permiso tp
        NATURAL JOIN    tipo_actividad ta;

CREATE OR REPLACE VIEW tipo_p
    AS
    SELECT *
    FROM actividad a
        NATURAL JOIN    tipo_actividad ta
        WHERE ta.tipo_p_r = 'P';

CREATE OR REPLACE VIEW tipo_r
    AS
    SELECT *
    FROM actividad a
        NATURAL JOIN    tipo_actividad ta
        WHERE ta.tipo_p_r = 'R';

CREATE OR REPLACE VIEW tipo_act__act
    AS
    SELECT *
    FROM actividad a
        NATURAL JOIN    tipo_actividad ta;

CREATE OR REPLACE VIEW campo__valor
    AS
    SELECT *
    FROM campo c
        NATURAL JOIN    valor v;

CREATE OR REPLACE VIEW act_completa
    AS
    SELECT *
    FROM tipo_act__act taa
        NATURAL JOIN campo__valor cv;


CREATE OR REPLACE VIEW act_participa
    AS
    SELECT *
    FROM actividad a 
    join participa p on a.id_actividad = p.id_act 
    natural join tipo_actividad;
