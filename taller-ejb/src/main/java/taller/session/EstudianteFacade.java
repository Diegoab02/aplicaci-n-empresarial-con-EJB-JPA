package taller.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import taller.entity.Curso;
import taller.entity.Estudiante;

@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante>
        implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "taller-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    @Override
    public boolean inscribirEnCurso(Long estudianteId, String cursoCodigo) {
        try {
            Estudiante e = em.find(Estudiante.class, estudianteId);
            Curso c = em.find(Curso.class, cursoCodigo);
            if (e == null || c == null) return false;
            if (e.getCursos().contains(c)) return false;
            if (c.getCuposDisponibles() <= 0) return false;
            e.getCursos().add(c);
            em.merge(e);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean retirarDeCurso(Long estudianteId, String cursoCodigo) {
        try {
            Estudiante e = em.find(Estudiante.class, estudianteId);
            Curso c = em.find(Curso.class, cursoCodigo);
            if (e == null || c == null) return false;
            if (!e.getCursos().contains(c)) return false;
            e.getCursos().remove(c);
            em.merge(e);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
