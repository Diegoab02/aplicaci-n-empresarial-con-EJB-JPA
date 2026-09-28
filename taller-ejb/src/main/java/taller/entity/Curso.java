package taller.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * EXTENSIÓN DE LA TAREA — Entidad Curso.
 * Atributos requeridos: codigo, nombre, creditos, semestre, cupos admitidos.
 */
@Entity
@Table(name = "CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCodigo",
            query = "SELECT c FROM Curso c WHERE c.codigo = :codigo")
})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO")
    private String codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CREDITOS")
    private int creditos;

    @Column(name = "SEMESTRE")
    private int semestre;

    @Column(name = "CUPOS_ADMITIDOS")
    private int cuposAdmitidos;

    @ManyToMany(mappedBy = "cursos")
    private Set<Estudiante> estudiantes = new HashSet<Estudiante>();

    public Curso() {
    }

    public Curso(String codigo, String nombre, int creditos, int semestre, int cuposAdmitidos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.semestre = semestre;
        this.cuposAdmitidos = cuposAdmitidos;
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

    @XmlTransient
    public Set<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Set<Estudiante> estudiantes) { this.estudiantes = estudiantes; }

    public int getCuposDisponibles() {
        return cuposAdmitidos - (estudiantes == null ? 0 : estudiantes.size());
    }

    @Override
    public int hashCode() {
        return (codigo != null ? codigo.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Curso)) return false;
        Curso other = (Curso) object;
        return !((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "taller.entity.Curso[ codigo=" + codigo + " ]";
    }
}
