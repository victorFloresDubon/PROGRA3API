package gt.edu.umg.progra3.pueblosapi.service;


import gt.edu.umg.progra3.pueblosapi.dao.HabitanteDao;
import gt.edu.umg.progra3.pueblosapi.dao.PuebloDao;
import gt.edu.umg.progra3.pueblosapi.model.Pueblo;

import java.util.HashMap;
import java.util.List;

public interface PuebloService {

    void crearPueblo(PuebloDao puebloDao);
    void crearHabitante(HabitanteDao habitanteDao);
    void guardarTodos(List<Pueblo> pueblos);
    void eliminarPuebloPorId(String id);
    void eliminarHabitante(String puebloId, String habitante);
    boolean isPuebloCreado(String id);
    List<HashMap<String, Object>> getPueblosResumen();
    void actualizar(String puebloId, String nombreActual, String nuevoNombre);
    boolean isHabitanteRegistrado(String puebloId, String habitanteId);
}
