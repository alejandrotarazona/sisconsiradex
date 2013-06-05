CREATE OR REPLACE VIEW elementos
                (id_catalogo, catalogo, id_campo, 
                    nombre_campo, tipo, id_elemento,valor) 
    AS
    SELECT id_catalogo as idcat, 
            nombre as catalogo, 
            id_campo as idcamp, 
            nombre_campo, 
            tipo_campo as tipo, 
            id_elemento as idelem, 
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
    SELECT ta.nombre_tipo_actividad, p.nombre
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


CREATE OR REPLACE VIEW  elemento_valor
    AS
    SELECT *
    FROM elemento_catalogo 
        NATURAL JOIN    valor_catalogo;

CREATE OR REPLACE VIEW participando 
    AS 
    SELECT * 
    FROM participa p 
    NATURAL LEFT OUTER JOIN usuario u
