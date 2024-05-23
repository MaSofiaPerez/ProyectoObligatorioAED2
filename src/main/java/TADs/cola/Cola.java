package TADs.cola;

public class Cola<T> implements ICola<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int cantidad;

    public Cola() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    @Override
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo(dato);
        if (isEmpty()) {
            primero = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
        }
        ultimo = nuevoNodo;
        cantidad++;
    }

    @Override
    public T desencolar() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = primero.getDato();
        primero = primero.getSiguiente();
        if (primero == null) {
            ultimo = null;
        }
        cantidad--;
        return dato;
    }

    @Override
    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return primero.getDato();
    }

    @Override
    public boolean isEmpty() {
        return primero == null;
    }

    @Override
    public void vaciar() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    @Override
    public int cantidadNodos() {
        return cantidad;
    }

}
