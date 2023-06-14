package gt.edu.umg.progra3.pueblosapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PuebloData {

    private List<Pueblo> pueblos;

    public PuebloData(){
        this.pueblos = new ArrayList<>();
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
        for(Pueblo pueblo : pueblos){
            if(pueblo.getNombre().equals(nombrePueblo)){
                pueblo.getArbolAvl().insertar(habitante);
            }
        }
    }

    public boolean isPuebloExistente(String id){
        Pueblo puebloStream = pueblos.stream()
                .filter(pueblo -> id.equals(pueblo.getNombre()))
                .findAny()
                .orElse(null);
        return puebloStream == null;
    }

}
