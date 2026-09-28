package taller.session;

import java.util.List;
import javax.ejb.Local;
import taller.entity.Curso;

@Local
public interface CursoFacadeLocal {

    void create(Curso curso);
    void edit(Curso curso);
    void remove(Curso curso);
    Curso find(Object id);
    List<Curso> findAll();
    int count();
}
