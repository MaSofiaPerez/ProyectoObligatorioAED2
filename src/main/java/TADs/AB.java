package TADs;

public class AB {

    private Nodo raiz;

    public AB(Nodo raiz) {
        this.raiz = raiz;
    }

    public int cantNodos() {
        return cantNodosRec(this.raiz);
    }

    private int cantNodosRec(Nodo nodo) {

        //1. CB
        if (nodo == null) {
            return 0;
        }
        //2. Llamada rec
        int cantIzq = cantNodosRec(nodo.getIzq());
        int cantDer = cantNodosRec(nodo.getDer());
        //3. Unir resultados
        return cantIzq + cantDer + 1;
    }

    public int cantHojas() {
        return cantHojas(this.raiz);
    }

    private int cantHojas(Nodo nodo) {
        if (nodo == null) {
            return 0; //???
        }
        if (nodo.getIzq() == null && nodo.getDer() == null) {
            return 1;
        }
        return cantHojas(nodo.getIzq()) + cantHojas(nodo.getDer());
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.esHoja()) {
            return 0;
        }
        //int alturaDer = alturaRec(nodo.getDer());
        //int alturaIzq = alturaRec(nodo.getIzq());
        //return 1 + Math.max( alturaDer, alturaIzq);
        return 1 + Math.max(alturaRec(nodo.getDer()), alturaRec(nodo.getIzq()));
    }

    public boolean todosPares() {
        return todosParesRec(raiz);
    }

    /*
        private boolean todosParesRec(Nodo nodo) {
        //1. Casos base
        if(nodo == null){
            return true; //??????????
        }
        if(nodo.getDato() % 2 != 0){
            return false;
        }
        boolean todosIzq = todosParesRec(nodo.getIzq());
        boolean todosDer = todosParesRec(nodo.getDer());
        return todosIzq && todosDer;
    }
     */
    private boolean todosParesRec(Nodo nodo) {
        //1. Casos base
        if (nodo == null) {
            return true; //??????????
        }
        if (nodo.getDato() % 2 != 0) {
            return false;
        }
        return todosParesRec(nodo.getIzq()) && todosParesRec(nodo.getDer());
    }

    private boolean todosParesRecV2(Nodo nodo) {
        //1. Casos base
        if (nodo == null) {
            return true; //??????????
        }
        return nodo.getDato() % 2 == 0 && todosParesRecV2(nodo.getIzq()) && todosParesRecV2(nodo.getDer());
    }

    public boolean iguales(AB a) {
        return igualesRec(raiz, a.raiz);
    }

    private boolean igualesRec(Nodo nodoA, Nodo nodoB) {
        if (nodoA == null && nodoB == null) {
            return true;
        }
        if (nodoA != null && nodoB != null) {
            if (nodoA.getDato() != nodoB.getDato()) {
                return false;
            }
            return igualesRec(nodoA.getDer(), nodoB.getDer()) && igualesRec(nodoA.getIzq(), nodoB.getIzq());
        }
        return false;
    }

    private boolean igualesRecV2(Nodo nodoA, Nodo nodoB) {
        if (nodoA == null && nodoB == null) {
            return true;
        }
        if (nodoA != null && nodoB != null) {
            return nodoA.getDato() == nodoB.getDato() && igualesRecV2(nodoA.getDer(), nodoB.getDer())
                    && igualesRecV2(nodoA.getIzq(), nodoB.getIzq());
        }
        return false;
    }

    public boolean equilibrado() {
        return equilibradoRec(raiz);
    }

    private boolean equilibradoRec(Nodo nodo) {
        if (nodo == null) {
            return true;
        }
        //  | altura(subárbol_izq) - altura(subárbol_der) | <= 1
        int alturaIzq = alturaRec(nodo.getIzq());
        int alturaDer = alturaRec(nodo.getDer());
        if (Math.abs(alturaIzq - alturaDer) > 1) {
            return false;
        }
        return equilibradoRec(nodo.getIzq()) && equilibradoRec(nodo.getDer());
    }

    private boolean equilibradoRecV2(Nodo nodo) {
        if (nodo == null) {
            return true;
        }
        //  | altura(subárbol_izq) - altura(subárbol_der) | <= 1
        int alturaIzq = alturaRec(nodo.getIzq());
        int alturaDer = alturaRec(nodo.getDer());
        return Math.abs(alturaIzq - alturaDer) <= 1 && equilibradoRecV2(nodo.getIzq()) && equilibradoRecV2(nodo.getDer());
    }

    public boolean pertenece(int dato) {
        return perteneceRec(raiz, dato);
    }

    private boolean perteneceRec(Nodo nodo, int dato) {
        if(nodo == null){
            return false;
        }
        if(nodo.getDato() == dato){
            return true;
        }
        return perteneceRec(nodo.getIzq(), dato) || perteneceRec(nodo.getDer(), dato);
    }

    public AB clon() {
        return new AB(clonRec(raiz));
    }

    public Nodo clonRec(Nodo nodo) {
        if(nodo == null){
            return null;
        }
        Nodo nuevo = new Nodo(nodo.getDato());
        nuevo.setDer( clonRec(nodo.getDer()) );
        nuevo.setIzq( clonRec(nodo.getIzq()) );
        return nuevo;
    }

    public Nodo clonRecV2(Nodo nodo) {
        if(nodo == null){
            return null;
        }
        return new Nodo(nodo.getDato(), clonRecV2(nodo.getIzq()), clonRecV2(nodo.getDer()));
    }

    public AB espejo() {
        return new AB(espejoRec(raiz));
    }

    private Nodo espejoRec(Nodo nodo) {
        if(nodo == null){
            return null;
        }
        return new Nodo(nodo.getDato(), espejoRec(nodo.getDer()), espejoRec(nodo.getIzq()));
    }

    public static class Nodo {
        private int dato;
        private Nodo izq;
        private Nodo der;

        public Nodo(int dato) {
            this.dato = dato;
        }

        public Nodo(int dato, Nodo izq, Nodo der) {
            this.dato = dato;
            this.izq = izq;
            this.der = der;
        }

        public boolean esHoja() {
            return this.izq == null && this.der == null;
        }

        public int getDato() {
            return dato;
        }

        public void setDato(int dato) {
            this.dato = dato;
        }

        public Nodo getIzq() {
            return izq;
        }

        public void setIzq(Nodo izq) {
            this.izq = izq;
        }

        public Nodo getDer() {
            return der;
        }

        public void setDer(Nodo der) {
            this.der = der;
        }

        @Override
        public String toString() {
            return dato + "";
        }
    }

}
