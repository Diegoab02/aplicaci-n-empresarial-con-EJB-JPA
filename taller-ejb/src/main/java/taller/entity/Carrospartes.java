package taller.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad Carrospartes — tabla intermedia N:M entre Carros y Partes.
 * Contiene la cantidad de una parte instalada en un carro.
 * La PK es compuesta (codigoParte, placaCarro) — ver CarrospartesPK.
 */
@Entity
@Table(name = "CARROSPARTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrospartes.findAll", query = "SELECT c FROM Carrospartes c"),
    @NamedQuery(name = "Carrospartes.findByCodigoParte",
        query = "SELECT c FROM Carrospartes c WHERE c.carrospartesPK.codigoParte = :codigoParte"),
    @NamedQuery(name = "Carrospartes.findByPlacaCarro",
        query = "SELECT c FROM Carrospartes c WHERE c.carrospartesPK.placaCarro = :placaCarro"),
    @NamedQuery(name = "Carrospartes.findByCantidad",
        query = "SELECT c FROM Carrospartes c WHERE c.cantidad = :cantidad")
})
public class Carrospartes implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CarrospartesPK carrospartesPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;

    @JoinColumn(name = "CODIGO_PARTE", referencedColumnName = "CODIGO_PARTE",
                insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partes partes;

    @JoinColumn(name = "PLACA_CARRO", referencedColumnName = "PLACA",
                insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Carros carros;

    public Carrospartes() {
    }

    public Carrospartes(CarrospartesPK carrospartesPK) {
        this.carrospartesPK = carrospartesPK;
    }

    public Carrospartes(CarrospartesPK carrospartesPK, int cantidad) {
        this.carrospartesPK = carrospartesPK;
        this.cantidad = cantidad;
    }

    public Carrospartes(String codigoParte, String placaCarro) {
        this.carrospartesPK = new CarrospartesPK(codigoParte, placaCarro);
    }

    public CarrospartesPK getCarrospartesPK() { return carrospartesPK; }
    public void setCarrospartesPK(CarrospartesPK carrospartesPK) {
        this.carrospartesPK = carrospartesPK;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Partes getPartes() { return partes; }
    public void setPartes(Partes partes) { this.partes = partes; }

    public Carros getCarros() { return carros; }
    public void setCarros(Carros carros) { this.carros = carros; }

    @Override
    public int hashCode() {
        return (carrospartesPK != null ? carrospartesPK.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Carrospartes)) return false;
        Carrospartes other = (Carrospartes) object;
        return !((this.carrospartesPK == null && other.carrospartesPK != null)
                || (this.carrospartesPK != null
                    && !this.carrospartesPK.equals(other.carrospartesPK)));
    }

    @Override
    public String toString() {
        return "taller.entity.Carrospartes[ carrospartesPK=" + carrospartesPK + " ]";
    }
}
