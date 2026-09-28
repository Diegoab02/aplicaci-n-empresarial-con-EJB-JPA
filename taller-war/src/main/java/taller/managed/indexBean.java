package taller.managed;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import taller.session.CarrospartesFacadeLocal;

/**
 * Managed Bean del formulario principal — CORRESPONDE AL VIDEO.
 * Captura codigoParte, placaCarro y cantidad, y llama al EJB
 * CarrospartesFacadeLocal.insertarCarroParte(...).
 */
@ManagedBean(name = "indexBean")
@SessionScoped
public class indexBean {

    private String codigoParte;
    private String placaCarro;
    private int cantidad;

    @EJB
    private CarrospartesFacadeLocal gestionCarrosPartes;

    public indexBean() {
    }

    public boolean insertarParteCarro() {
        return gestionCarrosPartes.insertarCarroParte(codigoParte, placaCarro, cantidad);
    }

    /**
     * Acción del botón del formulario. Devuelve el outcome que faces-config
     * usa para navegar a confirmacion.xhtml.
     */
    public String guardar() {
        boolean ok = insertarParteCarro();
        return ok ? "confirmacion" : "error";
    }

    /**
     * @return the codigoParte
     */
    public String getCodigoParte() {
        return codigoParte;
    }

    /**
     * @param codigoParte the codigoParte to set
     */
    public void setCodigoParte(String codigoParte) {
        this.codigoParte = codigoParte;
    }

    /**
     * @return the placaCarro
     */
    public String getPlacaCarro() {
        return placaCarro;
    }

    /**
     * @param placaCarro the placaCarro to set
     */
    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
