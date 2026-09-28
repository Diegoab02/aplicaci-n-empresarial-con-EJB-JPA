package taller.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import taller.entity.Carros;
import taller.entity.Carrospartes;
import taller.entity.CarrospartesPK;
import taller.entity.Partes;

@Stateless
public class CarrospartesFacade extends AbstractFacade<Carrospartes>
        implements CarrospartesFacadeLocal {

    @PersistenceContext(unitName = "taller-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarrospartesFacade() {
        super(Carrospartes.class);
    }

    /**
     * Asocia una parte a un carro con cierta cantidad.
     * Este es el método visible en el video (indexBean.java línea 31):
     *   gestionCarrosPartes.insertarCarroParte(codigoParte, placaCarro, cantidad);
     */
    @Override
    public boolean insertarCarroParte(String codigoParte, String placaCarro, int cantidad) {
        try {
            Carros carro = em.find(Carros.class, placaCarro);
            Partes parte = em.find(Partes.class, codigoParte);
            if (carro == null || parte == null) return false;

            CarrospartesPK pk = new CarrospartesPK(codigoParte, placaCarro);
            Carrospartes existente = em.find(Carrospartes.class, pk);
            if (existente != null) {
                existente.setCantidad(existente.getCantidad() + cantidad);
                em.merge(existente);
            } else {
                Carrospartes cp = new Carrospartes(pk, cantidad);
                cp.setCarros(carro);
                cp.setPartes(parte);
                em.persist(cp);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
