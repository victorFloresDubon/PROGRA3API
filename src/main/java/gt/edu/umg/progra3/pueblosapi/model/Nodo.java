package gt.edu.umg.progra3.pueblosapi.model;

import lombok.Data;

/**
 * Esta clase representará un nodo de la estructura del árbol AVL
 */
@Data
public class Nodo {

    // Área de datos
    private String habitante;
    // Índice de altura
    private int altura;
    // Nodo izquierdo y derecho respectivamente
    private Nodo izquierda, derecha;

    // Constructor por defecto
    public Nodo(String habitante){
        this.habitante = habitante;
        this.altura = 1;
    }



}
