package TADs;

import dominio.Pasajero;
import interfaz.Categoria;

import java.util.HashMap;
import java.util.Map;

public class ABBCategoria<T extends Comparable<T>> {

    private Nodo<T> raiz;
    private int cantidad;
    private Map<Categoria, ABB<Pasajero>> arbolesPorCategoria;

    public ABBCategoria() {
        arbolesPorCategoria = new HashMap<>();
        for (Categoria categoria : Categoria.values()) {
            arbolesPorCategoria.put(categoria, new ABB<Pasajero>());
        }
    }

    public void agregarPasajero(Pasajero pasajero, Categoria categoria) {
        ABB<Pasajero> arbol = arbolesPorCategoria.get(categoria);
        if (arbol != null) {
            arbol.agregar(pasajero);
        }
    }

    public ABB<Pasajero> obtenerArbol(Categoria categoria) {
        return arbolesPorCategoria.get(categoria);
    }


    public void agregar(T dato, Categoria categoria) {
        if (raiz == null) {
            raiz = new Nodo<>(dato);
        } else {
            agregarRec(raiz, dato, categoria);
        }
        cantidad++;
    }

    private void agregarRec(Nodo<T> nodo, T dato, Categoria categoria) {
        ABB<Pasajero> arbolCategoria = arbolesPorCategoria.get(categoria);
        if (arbolCategoria != null) {
            arbolCategoria.agregar((Pasajero) dato);
        }

        if (dato.compareTo(nodo.getDato()) > 0) { //der
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo<>(dato));
            } else {
                agregarRec(nodo.getDer(), dato, categoria);
            }
        } else { //izq
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo<>(dato));
            } else {
                agregarRec(nodo.getIzq(), dato, categoria);
            }
        }
    }
    public int largo() {
        return cantidad;
    }

}

