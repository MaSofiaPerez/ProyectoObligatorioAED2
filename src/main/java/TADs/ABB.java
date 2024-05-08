package TADs;

public class ABB<T extends Comparable<T>> {

    private Nodo<T> raiz;
    private int cantidad;

    public void agregar(T dato) {
        if (raiz == null) {
            raiz = new Nodo<>(dato);
        } else {
            agregarRec(raiz, dato);
        }
        cantidad++;
    }

    private void agregarRec(Nodo<T> nodo, T dato) {
        if (dato.compareTo(nodo.getDato()) > 0) { //der
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo<>(dato));
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

    public int largo() {
        return cantidad;
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

    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(Nodo<T> nodo) {
        if (nodo != null) {
            mostrar(nodo.getIzq());
            System.out.println(nodo.getDato());
            mostrar(nodo.getDer());
        }
    }

    public int cantidadElementosMayoresAUnNumero(int k){
        return cantElementosMayores(this.raiz, k);
    }

    private int cantElementosMayores(Nodo nodo, int k){
        if(nodo == null){
            return 0;
        }
        int cant = 0;
        if(nodo.getDato().equals(k)){
            cant++;
        }
        return cant + cantElementosMayores(nodo.getIzq(), k)+ cantElementosMayores(nodo.getDer(), k);
    }

//    private int cantElementosMayores(Nodo nodo, int k){
//        if(nodo == null){
//            return 0;
//        }
//        if(nodo.getDato() > k){
//            return 1 + cantElementosMayores(nodo.getIzq(), k)+ cantElementosMayores(nodo.getDer(), k);
//        }
//        return cantElementosMayores(nodo.getIzq(), k)+ cantElementosMayores(nodo.getDer(), k);
//    }

    public Lista obtenerCreciente(){
        Lista lista = new Lista();
        aplanarCrecienteRec(this.raiz, lista);
        return lista;
    }

    private void aplanarCrecienteRec(Nodo nodo, Lista lista){
        if(nodo != null){
            aplanarCrecienteRec(nodo.getIzq(), lista);
            lista.agregar(nodo.getDato()); //DEBERIA SER AGREGAR FINAL PERO NO ESTA HECHO EN TADLISTA
            aplanarCrecienteRec(nodo.getDer(), lista);
        }
    }

//    private void aplanarCrecienteRec(Nodo nodo, Lista lista){
//        if(nodo != null){
//            aplanarRec(nodo.getDer(), lista);
//            lista.agregarInicio(nodo.getDato());
//            aplanarRec(nodo.getIzq(), lista);
//        }
//    }

    private void aplanarDecrecienteRec(Nodo nodo, Lista lista){
        if(nodo != null){
            aplanarDecrecienteRec(nodo.getDer(), lista);
            lista.agregar(nodo.getDato()); //DEBERIA SER AGREGAR FINAL PERO NO ESTA HECHO EN TADLISTA
            aplanarDecrecienteRec(nodo.getIzq(), lista);
        }
    }

//    private void aplanarDecrecienteRec(Nodo nodo, Lista lista){
//        if(nodo != null){
//            aplanarRec(nodo.getIzq(), lista);
//            lista.agregarInicio(nodo.getDato());
//            aplanarRec(nodo.getDer(), lista);
//        }
//    }

    public Lista obtnerElementosMayores(int k){
        Lista lista = new Lista();
        obtenerRec(raiz, lista, k);
        return lista;
    }

    private void obtenerRec(Nodo nodo, Lista lista, int k){
        if(nodo != null){
            obtenerRec(nodo.getIzq(), lista, k);
            if(nodo.getDato().equals(k)){
                lista.agregar(nodo.getDato()); //DEBE SER AGEGAR FINAL PERO NO ESTSA IMPLEMENTADO EN TADLISTA
            }
            obtenerRec(nodo.getDer(), lista, k);
        }
    }

    public int obtenerNivel(int dato){
        return obtenerNivelRec(raiz, dato, 0);
    }

    //En AB
//    private int obtenerNivelRec(Nodo nodo,int dato,int nivelAtual){
//        if(nodo == null){
//            return -1;
//        }
//        if(nodo.getDato() == dato){
//            return nivelAtual;
//        }
//
//        return Math.max( obtenerNivelRec(nodo.getIzq(), dato, nivelAtual+1), obtenerNivelRec(nodo.getDer(), dato, nivelAtual+1));
//    }

    //En AB
    private int obtenerNivelRec(Nodo nodo,int dato,int nivelActual){
        if(nodo == null){
            return -1;
        }
        if(nodo.getDato().equals(dato)){
            return nivelActual;
        }
        int nivel = obtenerNivelRec(nodo.getIzq(), dato, nivelActual+1);
        if(nivel == -1 ){
            nivel = obtenerNivelRec(nodo.getDer(), dato, nivelActual+1);
        }
        return nivel;
    }

//    //En ABB
//    private int obtenerNivelRec(Nodo nodo,int dato,int nivelAtual){
//        if(nodo == null){
//            return -1;
//        }
//        if(nodo.getDato() == dato){
//            return nivelAtual;
//        }
//        if(nodo.getDato() > dato){
//            return obtenerNivelRec(nodo.getIzq(), dato, nivelAtual+1);
//        }else{
//            return obtenerNivelRec(nodo.getDer(), dato, nivelAtual+1);
//        }
//    }

    public void mostrarElementos(int nivel){
       mostrarElemNivelRec(raiz, nivel , 0 );
    }

    public void mostrarElemNivelRec(Nodo nodo,int nivel ,int nivelActual){
        if(nodo != null ){

            if(nivel == nivelActual){
                System.out.println(nodo.getDato());
            }
            if(nivelActual < nivel){
                mostrarElemNivelRec(nodo.getIzq(), nivel , nivelActual+1 );
                mostrarElemNivelRec(nodo.getDer(), nivel , nivelActual+1 );
            }

        }
    }

    public int altura(){
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo nodo){
        if(nodo == null){
            return -1;
        }
        return 1 + Math.max(alturaRec(nodo.getIzq()) , alturaRec(nodo.getDer()));
    }

    public void imprimirPorNiveles(){
        int altura = this.alturaRec(raiz);
        for(int i = 0 ; i <= altura ; i++){
            this.mostrarElementos(i);
        }
    }

//    public void imprimirPorNivelesV2(){
//        if(raiz != null){
//            Cola<Nodo> cola = new ColaImp<>();
//            cola.encolar(raiz);
//            while( !cola.esVacia() ){
//                Nodo aux = cola.desencolar();
//                System.out.println( aux.getDato() );
//                if(aux.getIzq() != null){
//                    cola.encolar(aux.getIzq());
//                }
//                if(aux.getDer() != null){
//                    cola.encolar(aux.getDer());
//                }
//            }
//        }
//    }
//
//    public void imprimirPorNivelesV2(){
//        Cola<Nodo> cola = new ColaImp<>();
//        cola.encolar(raiz);
//        while( !cola.esVacia() ){
//            Nodo aux = cola.desencolar();
//            if(aux != null){
//                System.out.println( aux.getDato() );
//                cola.encolar(aux.getIzq());
//                cola.encolar(aux.getDer());
//            }
//        }
//    }

    public void mostrarCantidadElementosDelNivel(int nivel){
        System.out.println( cantidadDeElementosDelNivel(nivel));
    }

    public int cantidadDeElementosDelNivel(int nivel){
        return cantEleRec(raiz, nivel , 0);
    }

    private int cantEleRec(Nodo nodo, int nivel, int nivelActual){
        if(nodo == null){
            return 0;
        }
        if(nivel == nivelActual){
            return 1;
        }
        return cantEleRec(nodo.getIzq(), nivel, nivelActual + 1) + cantEleRec(nodo.getDer(), nivel, nivelActual + 1);

    }

    public void imprimirPorNiveles(String separador){
        int altura = this.alturaRec(raiz);
        for(int i = 0 ; i < altura ; i++){
            this.mostrarElementos(i);
            System.out.println(separador);
        }
        this.mostrarElementos(altura);
    }
    private static class Nodo<T> {
        private T dato;
        private Nodo<T> izq;
        private Nodo<T> der;

        public Nodo(T dato) {
            this.dato = dato;
        }

        public Nodo(T dato, Nodo<T> izq, Nodo<T> der) {
            this.dato = dato;
            this.izq = izq;
            this.der = der;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getIzq() {
            return izq;
        }

        public void setIzq(Nodo<T> izq) {
            this.izq = izq;
        }

        public Nodo<T> getDer() {
            return der;
        }

        public void setDer(Nodo<T> der) {
            this.der = der;
        }
    }
}
