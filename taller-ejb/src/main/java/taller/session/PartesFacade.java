package taller.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import taller.entity.Partes;

@Stateless
public class PartesFacade extends AbstractFacade<Partes> implements PartesFacadeLocal {

    @PersistenceContext(unitName = "taller-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartesFacade() {
        super(Partes.class);
    }
}
