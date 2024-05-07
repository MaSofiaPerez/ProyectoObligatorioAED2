package TADs;

public class Lista<T> {

    private Nodo<T> inicio;
    private int cantidad;

    public void agregar(T dato) {
        inicio = new Nodo<>(dato, inicio);
        cantidad++;
    }

    public int largo() {
        return cantidad;
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

    private void mostrarIter() {
        Nodo<T> aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSig();
        }
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
