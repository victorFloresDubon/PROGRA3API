package gt.edu.umg.progra3.pueblosapi.service;


import gt.edu.umg.progra3.pueblosapi.dao.HabitanteDao;
import gt.edu.umg.progra3.pueblosapi.dao.PuebloDao;
import gt.edu.umg.progra3.pueblosapi.model.Pueblo;

import java.util.List;

public interface PuebloService {

    List<Pueblo> getPueblos();

    void crearPueblo(PuebloDao puebloDao);
    void crearHabitante(HabitanteDao habitanteDao);
    void guardarTodos(List<Pueblo> pueblos);
    void eliminarPuebloPorId(String id);
    boolean isPuebloCreado(String id);
}
