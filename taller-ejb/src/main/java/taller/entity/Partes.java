package taller.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad Partes — corresponde a la tabla PARTES.
 * PK: codigoParte.
 */
@Entity
@Table(name = "PARTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partes.findAll", query = "SELECT p FROM Partes p"),
    @NamedQuery(name = "Partes.findByCodigoParte", query = "SELECT p FROM Partes p WHERE p.codigoParte = :codigoParte"),
    @NamedQuery(name = "Partes.findByNombreParte", query = "SELECT p FROM Partes p WHERE p.nombreParte = :nombreParte")
})
public class Partes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_PARTE")
    private String codigoParte;

    @Size(max = 50)
    @Column(name = "NOMBRE_PARTE")
    private String nombreParte;

    @Column(name = "PRECIO")
    private Double precio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partes")
    private Collection<Carrospartes> carrospartesCollection;

    public Partes() {
    }

    public Partes(String codigoParte) {
        this.codigoParte = codigoParte;
    }

    public String getCodigoParte() { return codigoParte; }
    public void setCodigoParte(String codigoParte) { this.codigoParte = codigoParte; }

    public String getNombreParte() { return nombreParte; }
    public void setNombreParte(String nombreParte) { this.nombreParte = nombreParte; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    @XmlTransient
    public Collection<Carrospartes> getCarrospartesCollection() {
        return carrospartesCollection;
    }
    public void setCarrospartesCollection(Collection<Carrospartes> c) {
        this.carrospartesCollection = c;
    }

    @Override
    public int hashCode() {
        return (codigoParte != null ? codigoParte.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Partes)) return false;
        Partes other = (Partes) object;
        return !((this.codigoParte == null && other.codigoParte != null)
                || (this.codigoParte != null && !this.codigoParte.equals(other.codigoParte)));
    }

    @Override
    public String toString() {
        return "taller.entity.Partes[ codigoParte=" + codigoParte + " ]";
    }
}
