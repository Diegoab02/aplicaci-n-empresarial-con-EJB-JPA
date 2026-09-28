package taller.managed;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import taller.entity.Curso;
import taller.session.CursoFacadeLocal;

/**
 * EXTENSIÓN — Managed Bean de Curso.
 */
@ManagedBean(name = "cursoBean")
@SessionScoped
public class cursoBean {

    private String codigo;
    private String nombre;
    private int creditos;
    private int semestre;
    private int cuposAdmitidos;

    @EJB
    private CursoFacadeLocal gestionCursos;

    public String guardar() {
        Curso c = new Curso(codigo, nombre, creditos, semestre, cuposAdmitidos);
        gestionCursos.create(c);
        codigo = null; nombre = null;
        creditos = 0; semestre = 0; cuposAdmitidos = 0;
        return "cursos";
    }

    public List<Curso> getCursos() {
        return gestionCursos.findAll();
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }

    public int getCuposAdmitidos() { return cuposAdmitidos; }
    public void setCuposAdmitidos(int cuposAdmitidos) { this.cuposAdmitidos = cuposAdmitidos; }
}
