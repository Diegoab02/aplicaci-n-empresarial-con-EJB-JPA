package taller.session;

import java.util.List;
import javax.ejb.Local;
import taller.entity.Carrospartes;

@Local
public interface CarrospartesFacadeLocal {

    void create(Carrospartes carrospartes);
    void edit(Carrospartes carrospartes);
    void remove(Carrospartes carrospartes);
    Carrospartes find(Object id);
    List<Carrospartes> findAll();
    List<Carrospartes> findRange(int[] range);
    int count();

    /**
     * Método de negocio — asocia una parte a un carro con una cantidad.
     * (Visto en el video, indexBean.java línea 31.)
     */
    boolean insertarCarroParte(String codigoParte, String placaCarro, int cantidad);
}
