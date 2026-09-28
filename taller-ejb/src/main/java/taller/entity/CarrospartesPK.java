package taller.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Llave primaria compuesta de Carrospartes.
 * Formada por (codigoParte, placaCarro) — así una parte puede estar en
 * varios carros y un carro puede tener varias partes.
 */
@Embeddable
public class CarrospartesPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_PARTE")
    private String codigoParte;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PLACA_CARRO")
    private String placaCarro;

    public CarrospartesPK() {
    }

    public CarrospartesPK(String codigoParte, String placaCarro) {
        this.codigoParte = codigoParte;
        this.placaCarro = placaCarro;
    }

    public String getCodigoParte() { return codigoParte; }
    public void setCodigoParte(String codigoParte) { this.codigoParte = codigoParte; }

    public String getPlacaCarro() { return placaCarro; }
    public void setPlacaCarro(String placaCarro) { this.placaCarro = placaCarro; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoParte != null ? codigoParte.hashCode() : 0);
        hash += (placaCarro != null ? placaCarro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CarrospartesPK)) return false;
        CarrospartesPK other = (CarrospartesPK) object;
        if ((this.codigoParte == null && other.codigoParte != null)
                || (this.codigoParte != null && !this.codigoParte.equals(other.codigoParte))) {
            return false;
        }
        return !((this.placaCarro == null && other.placaCarro != null)
                || (this.placaCarro != null && !this.placaCarro.equals(other.placaCarro)));
    }

    @Override
    public String toString() {
        return "taller.entity.CarrospartesPK[ codigoParte=" + codigoParte
                + ", placaCarro=" + placaCarro + " ]";
    }
}
