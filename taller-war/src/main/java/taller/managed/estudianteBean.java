package taller.managed;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import taller.entity.Estudiante;
import taller.session.EstudianteFacadeLocal;

/**
 * EXTENSIÓN — Managed Bean de Estudiante.
 */
@ManagedBean(name = "estudianteBean")
@SessionScoped
public class estudianteBean {

    private String nombre;
    private Date fechaNacimiento;

    @EJB
    private EstudianteFacadeLocal gestionEstudiantes;

    public String guardar() {
        Estudiante e = new Estudiante(nombre, fechaNacimiento);
        gestionEstudiantes.create(e);
        nombre = null;
        fechaNacimiento = null;
        return "estudiantes";
    }

    public List<Estudiante> getEstudiantes() {
        return gestionEstudiantes.findAll();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}
