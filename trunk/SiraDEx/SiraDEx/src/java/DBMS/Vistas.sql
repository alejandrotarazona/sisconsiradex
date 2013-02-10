CREATE OR REPLACE VIEW elementos
                (id_catalogo, catalogo, nro_campos, id_campo, 
                    nombre_campo, tipo, id_elemento,valor) 
    AS
    SELECT id_catalogo as idcat, 
            nombre as catalogo, 
            nro_campos, 
            id_campo as idcamp, 
            nombre_campo, 
            tipo_campo as tipo, 
            id_elemento as idelem, 
            valor 
    FROM valor_catalogo 
        natural join elemento_catalogo 
        natural join catalogo 
        natural join campo_catalogo;

CREATE OR REPLACE VIEW coordinaciones
                (nombre_campo, tipo, id_elemento,valor)
    AS
    SELECT nombre_campo,
            tipo,
            id_elemento,
            valor
    FROM elementos
    WHERE catalogo = 'Coordinaciones';

CREATE OR REPLACE VIEW estudiantes
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombre,
            apellido,
            telefono,
            email
    FROM usuario u
        join catalogo c
    ON     u.tipo = c.id_cat
    WHERE  c.nombre = 'Estudiantes';

CREATE OR REPLACE VIEW obreros
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombre,
            apellido,
            telefono,
            email
    FROM usuario u
        join catalogo c
    ON     u.tipo = c.id_cat
    WHERE  c.nombre = 'Obrero';

CREATE OR REPLACE VIEW empleados
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombre,
            apellido,
            telefono,
            email
    FROM usuario u
        join catalogo c
    ON     u.tipo = c.id_cat
    WHERE  c.nombre = 'Empleado';

CREATE OR REPLACE VIEW profesores
                (nombre, apellido, telefono, email)
    AS
    SELECT u.nombre,
            apellido,
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