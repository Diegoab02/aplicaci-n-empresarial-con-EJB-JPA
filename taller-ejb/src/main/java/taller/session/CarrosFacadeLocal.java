package taller.session;

import java.util.List;
import javax.ejb.Local;
import taller.entity.Carros;

/**
 * Interfaz local del EJB CarrosFacade.
 */
@Local
public interface CarrosFacadeLocal {

    void create(Carros carros);
    void edit(Carros carros);
    void remove(Carros carros);
    Carros find(Object id);
    List<Carros> findAll();
    List<Carros> findRange(int[] range);
    int count();
}
