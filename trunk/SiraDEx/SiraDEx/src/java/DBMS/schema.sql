DROP TABLE USUARIO CASCADE;
DROP TABLE TIPO_ACTIVIDAD CASCADE;
DROP TABLE ACTIVIDAD CASCADE;
DROP TABLE VALOR CASCADE;
DROP TABLE CAMPO CASCADE;
DROP TABLE PERMISO CASCADE;
DROP TABLE TIENE_PERMISO CASCADE;
DROP TABLE LOG CASCADE;
DROP TABLE CATALOGO CASCADE;
DROP TABLE CAMPO_CATALOGO CASCADE;
DROP TABLE ELEMENTO_CATALOGO CASCADE;
DROP TABLE VALOR_CATALOGO CASCADE;
DROP TABLE PARTICIPA CASCADE;

CREATE TABLE PERMISO(
    id_permiso  SERIAL PRIMARY KEY,
    nombre      VARCHAR(20) NOT NULL
)
WITH (
  OIDS=FALSE
);

CREATE TABLE USUARIO(
    usbid             VARCHAR(20) NOT NULL,
    password            VARCHAR(20) NOT NULL,
    tipo                INT NOT NULL,
    nombre              VARCHAR(20),
    apellido            VARCHAR(20),
    email               VARCHAR(20),
    telefono            VARCHAR(15),

CONSTRAINT PK_usbid PRIMARY KEY (usbid),
CONSTRAINT FK_usuario__permiso 
            FOREIGN KEY (tipo) 
            REFERENCES PERMISO 
            ON UPDATE CASCADE 
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);


CREATE TABLE TIPO_ACTIVIDAD(
    id_tipo_actividad       SERIAL PRIMARY KEY,
    nombre_tipo_actividad   VARCHAR(140) NOT NULL,
    tipo_p_r                VARCHAR(1),
    nro_campos              SMALLINT NOT NULL,
    descripcion             VARCHAR(2000) NOT NULL,
    programa                INT NOT NULL,
    validador               INT NOT NULL,
    producto                VARCHAR(50),
    activo                  BOOLEAN DEFAULT TRUE
    
)
WITH (
  OIDS=FALSE
);

CREATE TABLE TIENE_PERMISO(
    id_tipo_actividad   INT NOT NULL,
    id_permiso          INT NOT NULL,

CONSTRAINT PK_tiene_permiso PRIMARY KEY (id_tipo_actividad, id_permiso),
CONSTRAINT FK_tiene_permiso__tipo_actividad 
            FOREIGN KEY (id_tipo_actividad) 
            REFERENCES TIPO_ACTIVIDAD 
            ON UPDATE CASCADE 
            ON DELETE CASCADE,
CONSTRAINT FK_tiene_permiso__permiso 
            FOREIGN KEY (id_permiso) 
            REFERENCES PERMISO 
            ON UPDATE CASCADE 
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE ACTIVIDAD(
    id_actividad      SERIAL,
    id_tipo_actividad integer NOT NULL,
    criterio          VARCHAR(13),
    valor             VARCHAR(3),
    validacion        VARCHAR(12),
    creador           VARCHAR(20) NOT NULL,
    fecha_creacion    VARCHAR(20) NOT NULL,
    modificador       VARCHAR(20) NOT NULL,
    fecha_modif       VARCHAR(20) NOT NULL,



CONSTRAINT PK_actividad PRIMARY KEY (id_actividad),
CONSTRAINT FK_actividad__usuario 
            FOREIGN KEY (creador) 
            REFERENCES USUARIO 
            ON UPDATE CASCADE 
            ON DELETE CASCADE,
CONSTRAINT FK_actividad__tipo_actividad 
            FOREIGN KEY (id_tipo_actividad) 
            REFERENCES TIPO_ACTIVIDAD 
            ON UPDATE CASCADE 
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE CAMPO(
    id_campo          SERIAL,
    id_tipo_actividad integer NOT NULL,
    nombre_campo      VARCHAR(100) NOT NULL,
    tipo_campo        VARCHAR(16) NOT NULL,
    longitud          NUMERIC(3),
    obligatorio       BOOLEAN NOT NULL,

CONSTRAINT PK_campo PRIMARY KEY (id_campo),
CONSTRAINT FK_campo__tipo_actividad 
            FOREIGN KEY (id_tipo_actividad) 
            REFERENCES TIPO_ACTIVIDAD 
            ON UPDATE CASCADE 
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE VALOR(
    id_campo     integer NOT NULL,
    id_actividad integer NOT NULL,
    valor        VARCHAR(1400),
 
CONSTRAINT PK_valor PRIMARY KEY (id_campo, id_actividad),
CONSTRAINT FK_valor__campo 
            FOREIGN KEY (id_campo) 
            REFERENCES CAMPO 
            ON UPDATE CASCADE 
            ON DELETE CASCADE,
CONSTRAINT FK_valor__actividad 
            FOREIGN KEY (id_actividad) 
            REFERENCES ACTIVIDAD 
            ON UPDATE CASCADE 
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE LOG(
    id_log  BIGSERIAL,
    accion  VARCHAR(4000) NOT NULL,
    fecha   DATE DEFAULT CURRENT_DATE,
    hora    TIME DEFAULT CURRENT_TIME,
    ip      VARCHAR(15) NOT NULL,
    usbid VARCHAR(20),

CONSTRAINT FK_log__usuario 
            FOREIGN KEY (usbid) 
            REFERENCES USUARIO 
            ON UPDATE CASCADE 
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE CATALOGO(
    id_cat      SERIAL PRIMARY KEY,
    nombre      VARCHAR(20) NOT NULL,
    nro_campos  INT NOT NULL
)
WITH (
  OIDS=FALSE
);


CREATE TABLE CAMPO_CATALOGO(
    id_campo        SERIAL PRIMARY KEY,
    id_cat          INT NOT NULL,
    nombre_campo    VARCHAR(100) NOT NULL,
    tipo_campo      VARCHAR(16) NOT NULL,

CONSTRAINT FK_campo_catalogo__catalogo 
            FOREIGN KEY (id_cat) 
            REFERENCES CATALOGO
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE ELEMENTO_CATALOGO(
    id_elemento     SERIAL PRIMARY KEY,
    id_catalogo     INT NOT NULL,

CONSTRAINT FK_elemento_catalogo__catalogo 
            FOREIGN KEY (id_catalogo) 
            REFERENCES CATALOGO
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE VALOR_CATALOGO(
    id_campo        INT NOT NULL,
    id_elemento     INT NOT NULL,
    valor           VARCHAR(1400) NOT NULL,

CONSTRAINT PK_valor_catalogo 
            PRIMARY KEY (id_campo, id_elemento, valor),
CONSTRAINT FK_valor_catalogo__campo_catalogo 
            FOREIGN KEY (id_campo) 
            REFERENCES CAMPO_CATALOGO
            ON UPDATE CASCADE
            ON DELETE CASCADE,
CONSTRAINT FK_valor_catalogo__elemento 
            FOREIGN KEY (id_elemento) 
            REFERENCES ELEMENTO_CATALOGO
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

CREATE TABLE PARTICIPA(
    id_act          INT NOT NULL,
    usbid         VARCHAR(20) NOT NULL,

CONSTRAINT PK_participa 
            PRIMARY KEY (id_act, usbid),
CONSTRAINT FK_participa__actividad 
            FOREIGN KEY (id_act) 
            REFERENCES ACTIVIDAD
            ON UPDATE CASCADE
            ON DELETE CASCADE,
CONSTRAINT FK_participa__usuario 
            FOREIGN KEY (usbid) 
            REFERENCES USUARIO
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
