package TADs;

import dominio.Aerolinea;
import dominio.Pasajero;

public class ABB<T extends Comparable<T>> {

    private ABB<T>.Nodo<T> raiz;
    int recorridos;
    public void agregar(T dato) {
        if (raiz == null) {
            raiz = new Nodo<T>(dato);
        } else {
            agregarRec(raiz, dato);
        }
    }

    public int getRecorridos(){return recorridos;}
    private void agregarRec(Nodo<T> nodo, T dato) {
        if (dato.compareTo(nodo.getDato()) > 0) { //der
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo<T>(dato));
            } else {
                agregarRec(nodo.getDer(), dato);
            }
        } else { //izq
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo<>(dato));
            } else {
                agregarRec(nodo.getIzq(), dato);
            }
        }
    }

    public boolean existe(T dato) {
        return existeRec(raiz, dato);
    }
    private boolean existeRec(Nodo<T> nodo, T dato){
        if(nodo == null){
            return false;
        }
        if(dato.compareTo(nodo.getDato()) == 0){
            return true;
        }else if (dato.compareTo(nodo.getDato())  < 0){
            return existeRec(nodo.getIzq(), dato);
        }else{
            return existeRec(nodo.getDer(), dato);
        }
    }
    public T obtener(T dato) {
        recorridos=0;
        return this.obtener(this.raiz, dato);
    }

    private T obtener(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        } else if ((nodo.getDato()).equals(dato)) {
            return nodo.getDato();
        } else {
            recorridos++;
            return dato.compareTo(nodo.getDato()) > 0 ? this.obtener(nodo.getDer(), dato) : this.obtener(nodo.getIzq(), dato);
        }
    }

    public Lista<T> listarAsc() {
        Lista<T> ret = new Lista<>();
        this.listarAsc(this.raiz, ret);
        return ret;
    }
    private void listarAsc(ABB<T>.Nodo<T> nodo, Lista<T> lista) {
        if (nodo != null) {
            this.listarAsc(nodo.getIzq(), lista);
            lista.agregarFinal(nodo.getDato());
            this.listarAsc(nodo.getDer(), lista);
        }

    }
    public Lista<T> listarDes() {
        Lista<T> ret = new Lista<>();
        this.listarDes(this.raiz, ret);
        return ret;
    }
    private void listarDes(ABB<T>.Nodo<T> nodo, Lista<T> lista) {
        if (nodo != null) {
            this.listarDes(nodo.getDer(), lista);
            lista.agregarFinal(nodo.getDato());
            this.listarDes(nodo.getIzq(), lista);
        }

    }
    private class Nodo<T extends Comparable<T>> {
        private T dato;
        private ABB<T>.Nodo<T> der;
        private ABB<T>.Nodo<T> izq;

        public Nodo(T dato) {
            this.dato = dato;
        }

        public Nodo(T dato, ABB<T>.Nodo<T> der, ABB<T>.Nodo<T> izq) {
            this.dato = dato;
            this.der = der;
            this.izq = izq;
        }

        public T getDato() {
            return this.dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public ABB<T>.Nodo<T> getDer() {
            return this.der;
        }

        public void setDer(ABB<T>.Nodo<T> der) {
            this.der = der;
        }

        public ABB<T>.Nodo<T> getIzq() {
            return this.izq;
        }

        public void setIzq(ABB<T>.Nodo<T> izq) {
            this.izq = izq;
        }
    }


}
