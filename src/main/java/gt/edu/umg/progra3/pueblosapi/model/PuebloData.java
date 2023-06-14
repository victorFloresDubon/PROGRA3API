package gt.edu.umg.progra3.pueblosapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class PuebloData {

    private List<Pueblo> pueblos;

    public PuebloData(){
        this.pueblos = new ArrayList<>();
    }

    /**
     * Obtiene un mapa de los pueblos que se tienen creados
     * @return HashMap de pueblos
     */
    public List<HashMap<String, Object>> getPueblosResumen(){
        List<HashMap<String, Object>> mapList = new ArrayList<>();
        HashMap<String, Object> map;
        for (Pueblo pueblo : pueblos){
            map = new HashMap<>();
            map.put("pueblo", pueblo.getNombre());
            map.put("poblacion", pueblo.getPoblacion());
            mapList.add(map);
        }

        return mapList;
    }
    /**
     * Crea un nuevo pueblo
     * @param nombre identificador/nombre del pueblo
     */
    public void crearPueblo(String nombre){
        pueblos.add(new Pueblo(nombre));
    }

    /**
     * Crea un nuevo habitante para un pueblo
     * @param nombrePueblo nombre del pueblo
     * @param habitante nombre del habitante a insertar
     */
    public void crearHabitante(String nombrePueblo, String habitante){
        // Buscamos el pueblo donde queremos insertar el registro
        Pueblo pueblo = buscarPuebloPorId(nombrePueblo);
        pueblo.getArbolAvl().insertar(habitante);
        // Actualizamos la población del pueblo
        pueblo.actualizarPoblacion();
    }

    /**
     * Elimina un pueblo entero
     * @param id nombre del pueblo
     */
    public void eliminarPuebloPorId(String id){
        // Buscamos el pueblo por su ID (nombre)
        Pueblo pueblo = buscarPuebloPorId(id);
        // Eliminamos el pueblo de la lista
        pueblos.remove(pueblo);
    }

    /**
     * Borra un habitante de un pueblo determinado
     * @param puebloId ID del pueblo
     * @param habitante ID del habitante a eliminar
     */
    public void eliminarHabitante(String puebloId, String habitante){
        // Buscamos el pueblo por su ID
        Pueblo pueblo = buscarPuebloPorId(puebloId);
        pueblo.getArbolAvl().eliminar(habitante);
    }
    /**
     * Valida si un pueblo existe creado dentro de la lista
     * @param id nombre del pueblo a validar
     * @return <code>true</code> si el pueblo existe
     */
    public boolean isPuebloExistente(String id){
        Pueblo puebloStream = buscarPuebloPorId(id);
        return puebloStream == null;
    }

    private Pueblo buscarPuebloPorId(String id){
        return pueblos.stream()
                .filter(pueblo -> id.equals(pueblo.getNombre()))
                .findAny()
                .orElse(null);
    }

    /**
     * Actualiza el nombre de un habitante dentro de un pueblo
     * @param puebloId Identificador del pueblo al que pertenece
     * @param nombreActual Nombre actual del habitante
     * @param nuevoNombre Nuevo nombre a ser definido
     */
    public void actualizarHabitante(String puebloId, String nombreActual, String nuevoNombre){
        Pueblo pueblo = buscarPuebloPorId(puebloId);
        pueblo.getArbolAvl().actualizar(nombreActual, nuevoNombre);
    }

    /**
     * Verifica si un habitante existe registrado dentro de un pueblo
     * @param puebloId Id del pueblo a buscar
     * @param habitanteId Id del habitante a buscar
     * @return
     */
    public boolean isHabitanteRegistrado(String puebloId, String habitanteId){
        Pueblo pueblo = buscarPuebloPorId(puebloId);
        Nodo buscar = pueblo.getArbolAvl().buscar(habitanteId);
        return buscar == null;
    }
}
