-- ============================================================================
-- Script SQL — Laboratorio 3 (Carros/Partes + Estudiante/Curso)
-- Motor: JavaDB (Derby) — incluido en GlassFish 4.1
-- Nota: JPA genera estas tablas automáticamente (schema-generation drop-and-create).
--       Este script se incluye como documentación del modelo relacional.
-- ============================================================================

-- ---------- Tablas del video (Carros/Partes) ----------

CREATE TABLE CARROS (
    PLACA   VARCHAR(10) NOT NULL PRIMARY KEY,
    MARCA   VARCHAR(30),
    MODELO  VARCHAR(30)
);

CREATE TABLE PARTES (
    CODIGO_PARTE VARCHAR(10) NOT NULL PRIMARY KEY,
    NOMBRE_PARTE VARCHAR(50),
    PRECIO       DOUBLE
);

-- Tabla puente N:M — clave primaria compuesta (CarrospartesPK en JPA)
CREATE TABLE CARROSPARTES (
    CODIGO_PARTE VARCHAR(10) NOT NULL,
    PLACA_CARRO  VARCHAR(10) NOT NULL,
    CANTIDAD     INT NOT NULL,
    PRIMARY KEY (CODIGO_PARTE, PLACA_CARRO),
    CONSTRAINT FK_CP_PARTE FOREIGN KEY (CODIGO_PARTE)
        REFERENCES PARTES(CODIGO_PARTE),
    CONSTRAINT FK_CP_CARRO FOREIGN KEY (PLACA_CARRO)
        REFERENCES CARROS(PLACA)
);

-- ---------- Extensión (Estudiante/Curso N:M) ----------

CREATE TABLE ESTUDIANTE (
    ID               BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOMBRE           VARCHAR(120) NOT NULL,
    FECHA_NACIMIENTO DATE
);

CREATE TABLE CURSO (
    CODIGO          VARCHAR(20) NOT NULL PRIMARY KEY,
    NOMBRE          VARCHAR(120) NOT NULL,
    CREDITOS        INT,
    SEMESTRE        INT,
    CUPOS_ADMITIDOS INT
);

CREATE TABLE ESTUDIANTE_CURSO (
    ESTUDIANTE_ID BIGINT NOT NULL,
    CURSO_CODIGO  VARCHAR(20) NOT NULL,
    PRIMARY KEY (ESTUDIANTE_ID, CURSO_CODIGO),
    CONSTRAINT FK_EC_EST FOREIGN KEY (ESTUDIANTE_ID)
        REFERENCES ESTUDIANTE(ID) ON DELETE CASCADE,
    CONSTRAINT FK_EC_CUR FOREIGN KEY (CURSO_CODIGO)
        REFERENCES CURSO(CODIGO) ON DELETE CASCADE
);

-- Datos de ejemplo
INSERT INTO CARROS (PLACA, MARCA, MODELO) VALUES ('ABC123', 'Renault', 'Sandero');
INSERT INTO PARTES (CODIGO_PARTE, NOMBRE_PARTE, PRECIO) VALUES ('P001', 'Bujía', 25000);
INSERT INTO CURSO  (CODIGO, NOMBRE, CREDITOS, SEMESTRE, CUPOS_ADMITIDOS)
    VALUES ('ARQ801', 'Arquitecturas y Dispositivos Convergentes', 3, 8, 30);
