# Laboratorio 3 — Aplicación empresarial con EJB + JPA

Aplicación Java EE 7 tipo **Enterprise Application (EAR)** que implementa la capa
de persistencia (JPA) sobre la capa de lógica de negocio (EJB), siguiendo el
tutorial del profesor Gilberto Pedraza (JEE+EJB+JPA Parte 4) y extendiéndolo
con el componente de Estudiantes y Cursos requerido por el enunciado.

- **Autor:** Diego Alejandro Betancur Herrera
- **Materia:** Arquitectura y Dispositivos Convergentes — Universidad Piloto de Colombia
- **Repositorio:** https://github.com/Diegoab02

## Objetivos cumplidos

1. ✅ Desarrollar la capa de persistencia a partir de la capa de lógica de negocio.
2. ✅ Familiarizarse con la especificación **EJB** (Session Beans + Facade).
3. ✅ Reproducir el tutorial base — **Carros / Partes / Carrospartes** con PK compuesta.
4. ✅ Extender el proyecto para incluir **Cursos** con: código, nombre, créditos, semestre, cupos.
5. ✅ Implementar la relación **muchos-a-muchos** Estudiante ↔ Curso.

## Tecnologías

| Componente | Versión |
|---|---|
| Java | 7/8 |
| Java EE | 7 |
| Servidor | GlassFish 4.1 |
| JPA | 2.1 (EclipseLink) |
| EJB | 3.2 |
| JSF | 2.2 |
| Motor BD | JavaDB (Derby) — embebido en GlassFish |
| Build | Maven 3 |
| IDE | NetBeans 8.0.2 |

## Arquitectura — proyecto EAR de tres módulos

```
taller/                              ← proyecto padre (POM)
├── taller-ejb/                      MÓDULO EJB — negocio + persistencia
│   └── src/main/java/taller/
│       ├── entity/                  ← Entidades JPA
│       │   ├── Carros.java
│       │   ├── Partes.java
│       │   ├── Carrospartes.java    ← relación N:M con @EmbeddedId
│       │   ├── CarrospartesPK.java  ← PK compuesta
│       │   ├── Estudiante.java      ← EXTENSIÓN
│       │   └── Curso.java           ← EXTENSIÓN
│       └── session/                 ← Session Beans (EJB Stateless)
│           ├── AbstractFacade.java          ← facade genérico
│           ├── CarrosFacade + CarrosFacadeLocal
│           ├── PartesFacade + PartesFacadeLocal
│           ├── CarrospartesFacade + CarrospartesFacadeLocal  ← insertarCarroParte()
│           ├── EstudianteFacade + EstudianteFacadeLocal      ← inscribirEnCurso()
│           └── CursoFacade + CursoFacadeLocal
│
├── taller-war/                      MÓDULO WEB — presentación JSF
│   └── src/main/
│       ├── java/taller/managed/
│       │   ├── indexBean.java         ← Managed Bean del video
│       │   ├── estudianteBean.java    ← EXTENSIÓN
│       │   ├── cursoBean.java         ← EXTENSIÓN
│       │   └── inscripcionBean.java   ← EXTENSIÓN
│       └── webapp/
│           ├── index.xhtml            ← formulario del video
│           ├── confirmacion.xhtml
│           ├── estudiantes.xhtml, cursos.xhtml, inscripcion.xhtml
│           ├── resources/css/estilos.css
│           └── WEB-INF/{web.xml, faces-config.xml}
│
└── taller-ear/                      MÓDULO EAR — empaqueta EJB + WAR
    └── src/main/application/META-INF/application.xml
```

## Modelo de datos

### Carros/Partes (tutorial base)

```
CARROS(placa PK, marca, modelo)
PARTES(codigo_parte PK, nombre_parte, precio)

CARROSPARTES(codigo_parte, placa_carro, cantidad)
  PK = (codigo_parte, placa_carro)          ← llave compuesta
  FK codigo_parte → PARTES
  FK placa_carro → CARROS
```

### Estudiante/Curso (extensión)

```
ESTUDIANTE(id PK auto, nombre, fecha_nacimiento)
CURSO(codigo PK, nombre, creditos, semestre, cupos_admitidos)

ESTUDIANTE_CURSO(estudiante_id, curso_codigo)   ← tabla puente N:M
  PK = (estudiante_id, curso_codigo)
```

## Configuración del DataSource en GlassFish 4.1

1. Iniciar el servidor: `asadmin start-domain`
2. Abrir consola: `http://localhost:4848`
3. **Resources → JDBC → JDBC Connection Pools → New**
   - Pool Name: `tallerPool`
   - Resource Type: `javax.sql.DataSource`
   - Database Driver Vendor: **JavaDB** (viene con GlassFish)
4. **Resources → JDBC → JDBC Resources → New**
   - JNDI Name: `jdbc/tallerDS`
   - Pool Name: `tallerPool`

> El nombre `jdbc/tallerDS` coincide con `<jta-data-source>` en `persistence.xml`.

## Compilación y despliegue

### Desde NetBeans 8.0.2

1. `File → Open Project` → seleccionar carpeta `taller`
2. Clic derecho al proyecto EAR (`taller-ear`) → **Clean and Build**
3. Clic derecho → **Run**
4. URL: `http://localhost:8080/taller-war/`

### Desde línea de comandos

```bash
cd taller
mvn clean install
asadmin deploy taller-ear/target/taller.ear
```

## Uso de la aplicación

| Ruta | Descripción |
|------|-------------|
| `/taller-war/` | Formulario del video — asocia una parte a un carro |
| `/taller-war/estudiantes.xhtml` | Registrar estudiantes (edad calculada) |
| `/taller-war/cursos.xhtml` | Registrar cursos con código, créditos, semestre y cupos |
| `/taller-war/inscripcion.xhtml` | Inscribir/retirar estudiantes en cursos (N:M) |



