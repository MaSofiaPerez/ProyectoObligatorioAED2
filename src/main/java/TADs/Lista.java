package TADs;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> {
    private Nodo<T> inicio;
    private int cantidad;

    public void agregar(T dato) {
        inicio = new Nodo<>(dato, inicio);
        cantidad++;
    }

    public int largo() {
        return cantidad;
    }

    public T get(int posactual) {
        if (posactual < 0 || posactual >= cantidad) {
            return null;
        }
        Nodo<T> actual = inicio;
        for (int i = 0; i < posactual; i++) {
            actual = actual.getSig();
        }
        return actual.getDato();
    }

    public void agregarFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (this.estaVacia()) {
            this.inicio = nuevoNodo;
        } else {
            Nodo<T> actual = this.inicio;
            while (actual.getSig() != null) {
                actual = actual.getSig();
            }
            actual.setSig(nuevoNodo);
        }
        this.cantidad++;
    }

    private boolean estaVacia() {
        return inicio == null;
    }

    public boolean existe(T dato) {
        return existeRec(inicio, dato);
    }

    private boolean existeRec(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return false;
        }
        return nodo.getDato().equals(dato) || existeRec(nodo.getSig(), dato);
    }

    private boolean existeIter(T dato) {
        Nodo<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    public void mostrar() {
        System.out.println();
        //mostrarIter();
        mostrarRec(inicio);
    }

    private void mostrarRec(Nodo<T> nodo) {
        if (nodo != null) {
            mostrarRec(nodo.getSig());
            System.out.println(nodo.getDato());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Nodo<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.getDato();
                aux = aux.getSig();
                return dato;
            }

            @Override
            public void remove() {
            }
        };
    }

    private static class Nodo<T> {

        private T dato;
        private Nodo<T> sig;

        public Nodo(T dato) {
            this.dato = dato;
        }

        public Nodo(T dato, Nodo<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getSig() {
            return sig;
        }

        public void setSig(Nodo<T> sig) {
            this.sig = sig;
        }
    }


}
