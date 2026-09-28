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
 * Entidad Carros — corresponde a la tabla CARROS.
 * PK: placa.
 */
@Entity
@Table(name = "CARROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carros.findAll", query = "SELECT c FROM Carros c"),
    @NamedQuery(name = "Carros.findByPlaca", query = "SELECT c FROM Carros c WHERE c.placa = :placa"),
    @NamedQuery(name = "Carros.findByMarca", query = "SELECT c FROM Carros c WHERE c.marca = :marca"),
    @NamedQuery(name = "Carros.findByModelo", query = "SELECT c FROM Carros c WHERE c.modelo = :modelo")
})
public class Carros implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PLACA")
    private String placa;

    @Size(max = 30)
    @Column(name = "MARCA")
    private String marca;

    @Size(max = 30)
    @Column(name = "MODELO")
    private String modelo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carros")
    private Collection<Carrospartes> carrospartesCollection;

    public Carros() {
    }

    public Carros(String placa) {
        this.placa = placa;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    @XmlTransient
    public Collection<Carrospartes> getCarrospartesCollection() {
        return carrospartesCollection;
    }
    public void setCarrospartesCollection(Collection<Carrospartes> c) {
        this.carrospartesCollection = c;
    }

    @Override
    public int hashCode() {
        return (placa != null ? placa.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Carros)) return false;
        Carros other = (Carros) object;
        return !((this.placa == null && other.placa != null)
                || (this.placa != null && !this.placa.equals(other.placa)));
    }

    @Override
    public String toString() {
        return "taller.entity.Carros[ placa=" + placa + " ]";
    }
}
