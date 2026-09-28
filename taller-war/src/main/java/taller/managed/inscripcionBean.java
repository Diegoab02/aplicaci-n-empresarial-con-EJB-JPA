package taller.managed;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import taller.entity.Curso;
import taller.entity.Estudiante;
import taller.session.CursoFacadeLocal;
import taller.session.EstudianteFacadeLocal;

/**
 * EXTENSIÓN — Managed Bean para inscribir estudiantes en cursos.
 * (Relación N:M — un estudiante toma varios cursos, un curso lo toman varios estudiantes.)
 */
@ManagedBean(name = "inscripcionBean")
@SessionScoped
public class inscripcionBean {

    private Long estudianteId;
    private String cursoCodigo;
    private String mensaje;

    @EJB
    private EstudianteFacadeLocal gestionEstudiantes;

    @EJB
    private CursoFacadeLocal gestionCursos;

    public String inscribir() {
        boolean ok = gestionEstudiantes.inscribirEnCurso(estudianteId, cursoCodigo);
        mensaje = ok ? "Inscripción exitosa." : "No se pudo inscribir (sin cupo, ya inscrito o datos inválidos).";
        return "inscripcion";
    }

    public String retirar() {
        boolean ok = gestionEstudiantes.retirarDeCurso(estudianteId, cursoCodigo);
        mensaje = ok ? "Retiro exitoso." : "No se pudo retirar.";
        return "inscripcion";
    }

    public List<Estudiante> getEstudiantes() { return gestionEstudiantes.findAll(); }
    public List<Curso> getCursos() { return gestionCursos.findAll(); }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public String getCursoCodigo() { return cursoCodigo; }
    public void setCursoCodigo(String cursoCodigo) { this.cursoCodigo = cursoCodigo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
