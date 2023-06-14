package gt.edu.umg.progra3.pueblosapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representará un árbol AVL, junto con todas las operaciones que conlleva
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AVL {

    // Raíz del árbol
    private Nodo raiz;

    /**
     * Obtiene la altura de un nodo
     * @param nodo nodo a consultar
     * @return altura del nodo indicado
     */
    public int getAlturaNodo(Nodo nodo){
        if(nodo == null)
            return -1;
        return nodo.getAltura();
    }

    /**
     * Obtiene el máximo entre dos números enteros
     * @param a Valor a
     * @param b Valor b
     * @return Valor máximo entre ambos números
     */
    public int getMaximo(int a, int b){
        return Math.max(a, b);
    }

    /**
     * Ejecuta la rotación hacia la derecha
     * @param y Nodo raíz del cual se hará la rotación
     * @return El nodo que será la nueva raíz
     */
    private Nodo rotarDerecha(Nodo y){
        // Nodo izquierdo de Y
        Nodo x = y.getIzquierda();
        // Nodo derecho de Y
        Nodo z = x.getDerecha();

        // Realizamos la rotación de los nodos
        x.setDerecha(y);
        y.setIzquierda(z);

        // Actualizamos las alturas
        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    /**
     * Ejecuta la rotación hacia la izquierda
     * @param y Nodo raíz del cual se hará la rotación
     * @return El nodo que será la nueva raíz
     */
    private Nodo rotarIzquierda(Nodo y){
        // Nodo izquierdo de Y
        Nodo x = y.getDerecha();
        // Nodo derecho de Y
        Nodo z = x.getIzquierda();

        // Realizamos la rotación de los nodos
        x.setIzquierda(y);
        y.setDerecha(z);

        // Actualizamos las alturas
        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    /**
     * Actualiza la altura de un nodo
     * @param nodo nodo a actualizar
     */
    private void actualizarAltura(Nodo nodo){
        int newAltura = getMaximo(getAlturaNodo(nodo.getIzquierda()), getAlturaNodo(nodo.getDerecha())) + 1;
        nodo.setAltura(newAltura);
    }

    /**
     * Obtiene el factor de balance de un nodo
     * @param nodo
     * @return factor de balance
     */
    public int getBalance(Nodo nodo){
        if (nodo == null)
            return 0;
        return getAlturaNodo(nodo.getIzquierda()) - getAlturaNodo(nodo.getDerecha());
    }

    /**
     * Método público para insertar un nuevo registro al arbol
     * @param habitante nombre del habitante
     */
    public void insertar(String habitante){
        raiz = insertarNodo(raiz, habitante);
    }

    /**
     * Método recursivo privado
     * Inserta un nuevo registro al árbol
     * @param nuevo nodo nuevo a ser creado
     * @param habitante datos de la nueva persona
     * @return nodo creado
     */
    private Nodo insertarNodo(Nodo nuevo, String habitante){
        // Realizamos la insersión normal de un árbol binario de búsqueda
        if (nuevo == null)
            return new Nodo(habitante);

        // Hacemos la comparación del nombre de la persona
        if(habitante.compareTo(nuevo.getHabitante()) < 0){
            nuevo.setIzquierda(insertarNodo(nuevo.getIzquierda(), habitante));
        } else if (habitante.compareTo(nuevo.getHabitante()) > 0) {
            nuevo.setDerecha(insertarNodo(nuevo.getDerecha(), habitante));
        } else {
            return nuevo; // No se permitirán nombres de personas duplicados
        }

        // Actualizamos la altura del nodo actual
        actualizarAltura(nuevo);
        // Obtenemos el factor de equilibrio del nodo actual
        int balance = getBalance(nuevo);

        // Casos de desequilibrio y rotaciones
        // Izquierda - Izquierda
        if (balance > 1 && habitante.compareTo(nuevo.getIzquierda().getHabitante()) < 0){
            return rotarDerecha(nuevo);
        }

        // Derecha - Derecha
        if (balance < -1 && habitante.compareTo(nuevo.getDerecha().getHabitante()) > 0){
            return rotarIzquierda(nuevo);
        }

        // Izquierda - Derecha
        if (balance > 1 && habitante.compareTo(nuevo.getIzquierda().getHabitante()) > 0){
            nuevo.setIzquierda(rotarIzquierda(nuevo.getIzquierda()));
            return rotarDerecha(nuevo);
        }

        // Derecha - Izquierda
        if (balance < -1 && habitante.compareTo(nuevo.getDerecha().getHabitante()) < 0){
            nuevo.setDerecha(rotarDerecha(nuevo.getDerecha()));
            return rotarIzquierda(nuevo);
        }

        return nuevo;
    }

    /**
     * Busca un nodo de acuerdo al valor clave
     * @param nombrePersona valor clave, en este caso el nombre de la persona
     * @return nodo del árbol donde se encuentra
     */
    public Nodo buscar(String nombrePersona){
        // Nos posicionamos en la raíz como nodo actual
        Nodo actual = raiz;
        while (actual != null){
            if (actual.getHabitante().equals(nombrePersona)){
                break;
            }
            actual = actual.getHabitante().compareTo(nombrePersona) < 0 ? actual.getIzquierda() : actual.getDerecha();
        }
        return actual;
    }

    /**
     * Devuelve una lista de los nodos por medio de recorrido InOrden
     * @return lista de nodos
     */
    public List<Nodo> getInordenList(){
        return getInordenList(this.raiz);
    }

    private List<Nodo> getInordenList(Nodo nodo){
        List<Nodo> list = new ArrayList<>();
        if (nodo != null){
            getInordenList(nodo.getIzquierda());
            list.add(nodo);
            getInordenList(nodo.getDerecha());
        }
        return list;
    }

    /**
     * Obtiene la lista de los datos clave
     * @return lista de las llaves
     */
    public List<String> getKeys(){
        return getKeysInorden(raiz);
    }

    public List<String> getKeysInorden(Nodo nodo){
        List<String> list = new ArrayList<>();
        if (nodo != null){
            getKeysInorden(nodo.getIzquierda());
            list.add(nodo.getHabitante());
            getKeysInorden(nodo.getDerecha());
        }
        return list;
    }

    public void eliminar(String habitante){
        raiz = eliminarNodo(raiz, habitante);
    }

    private Nodo eliminarNodo(Nodo nodo, String habitante){
        if(nodo == null)
            return nodo;

        if(nodo.getHabitante().compareTo(habitante) < 0){
            nodo.setIzquierda(eliminarNodo(nodo.getIzquierda(), habitante));
        }

        if(nodo.getHabitante().compareTo(habitante) > 0){
            nodo.setDerecha(eliminarNodo(nodo.getDerecha(), habitante));
        }

        if(nodo.getIzquierda() == null || nodo.getDerecha() == null){
            nodo = (nodo.getIzquierda() == null) ? nodo.getDerecha() : nodo.getIzquierda();
        } else {
            Nodo hijoMasIzquierdo = hijoMasIzquierdo(nodo.getDerecha());
            nodo.setHabitante(hijoMasIzquierdo.getHabitante());
            nodo.setDerecha(eliminarNodo(nodo.getDerecha(), nodo.getHabitante()));
        }
        if(nodo != null){
            nodo = rebalancear(nodo);
        }
        return nodo;
    }

    private Nodo hijoMasIzquierdo(Nodo nodo){
        Nodo actual = nodo;
        // Iteramos hasta encontrar el nodo que se encuentre más a la izquierda
        while (actual.getIzquierda() != null){
            actual = actual.getIzquierda();
        }
        return actual;
    }

    private Nodo rebalancear(Nodo nodo){
        actualizarAltura(nodo);
        int balance = getBalance(nodo);
        if(balance > 1){
            if(nodo.getDerecha().getDerecha().getAltura() > nodo.getDerecha().getIzquierda().getAltura()){
                nodo = rotarIzquierda(nodo);
            } else {
                nodo.setDerecha(rotarDerecha(nodo.getDerecha()));
                nodo = rotarIzquierda(nodo);
            }
        } else if (balance < -1) {
            if(nodo.getIzquierda().getIzquierda().getAltura() > nodo.getDerecha().getIzquierda().getAltura()){
                nodo = rotarDerecha(nodo);
            } else {
                nodo.setIzquierda(rotarIzquierda(nodo.getIzquierda()));
                nodo = rotarDerecha(nodo);
            }
        }

        return nodo;
    }

    public void actualizar(String nuevoHabitante){

    }

}
