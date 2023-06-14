package gt.edu.umg.progra3.pueblosapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pueblo {

    private String nombre;
    private int poblacion;
    private AVL arbolAvl;

    public Pueblo(String nombre, int poblacion){
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.arbolAvl = new AVL();
    }

    public Pueblo(String nombre){
        this.nombre = nombre;
        this.poblacion = 0;
        this.arbolAvl = new AVL();
    }

}
