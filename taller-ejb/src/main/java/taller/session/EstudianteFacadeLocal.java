package taller.session;

import java.util.List;
import javax.ejb.Local;
import taller.entity.Estudiante;

@Local
public interface EstudianteFacadeLocal {

    void create(Estudiante estudiante);
    void edit(Estudiante estudiante);
    void remove(Estudiante estudiante);
    Estudiante find(Object id);
    List<Estudiante> findAll();
    int count();

    /** Método de negocio — inscribe estudiante en un curso (relación N:M). */
    boolean inscribirEnCurso(Long estudianteId, String cursoCodigo);
    boolean retirarDeCurso(Long estudianteId, String cursoCodigo);
}
