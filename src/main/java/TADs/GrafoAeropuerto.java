package TADs;
import TADs.cola.Cola;
import dominio.Aeropuerto;
import dominio.Conexion;
import dominio.Vuelo;

public class GrafoAeropuerto {

    int topeAeropuertos;
    int cantidad;

    Aeropuerto[] vertices;
    Arista[][] matAdy;

    public GrafoAeropuerto(int unTope, int cantidad) {
        this.topeAeropuertos = unTope;
        this.cantidad = cantidad;
        this.vertices = new Aeropuerto[topeAeropuertos];
        this.matAdy = new Arista[topeAeropuertos][topeAeropuertos];
        for (int i = 0; i < topeAeropuertos; i++) {
            for (int j = 0; j < topeAeropuertos; j++) {
                matAdy[i][j] = new Arista();
            }
        }
    }

    public boolean esLleno() {
        return cantidad == topeAeropuertos;
    }

    public boolean esVacio() {
        return cantidad == 0;
    }

    // PRE: !esLleno()
    private int obtenerPosLibre() {
        for (int i = 0; i < topeAeropuertos; i++) {
            if (vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(Aeropuerto vertice) {
        if (vertice != null) {
            for (int i = 0; i < topeAeropuertos; i++) {
                if (vertice.equals(vertices[i])) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    public Aeropuerto obtenerVertice(String codigo) {
        for (int i = 0; i < topeAeropuertos; i++) {
            if (vertices[i] != null && codigo.equals(vertices[i].getCodigo())) {
                return vertices[i];
            }
        }
        return null;
    }

    // PRE: !esLleno && !existeVertice
    public void agregarVertice(Aeropuerto vert) {
        int pos = obtenerPosLibre();
        vertices[pos] = vert;
        cantidad++;
    }

    // PRE: existeVertice
    public void borrarVertice(Aeropuerto vert) {
        int pos = obtenerPos(vert);
        vertices[pos] = null;
        for (int i = 0; i < topeAeropuertos; i++) {
            matAdy[pos][i].setExiste(false);
            matAdy[i][pos].setExiste(false);
        }
        cantidad--;
    }

    public boolean existeVertice(Aeropuerto vert) {
        return obtenerPos(vert) != -1;
    }

    // existeVertice(origen) && existeVertice(destino) && !existeArista
    public void agregarArista(Aeropuerto origen, Aeropuerto destino, double kilometros) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].setPeso(kilometros);

    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existeArista(Aeropuerto origen, Aeropuerto destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        return matAdy[posOrigen][posDestino].isExiste();
    }

    // existeVertice(origen) && existeVertice(destino) && existeArista
    public void borrarArista(Aeropuerto origen, Aeropuerto destino) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdy[posOrigen][posDestino].setExiste(false);

    }

    public Lista<Aeropuerto> verticesAdyacentes(Aeropuerto vert) {
        Lista<Aeropuerto> retorno = new Lista<>();
        int pos = obtenerPos(vert);
        for (int i = 0; i < topeAeropuertos; i++) {
            if (matAdy[pos][i].isExiste()) {
                retorno.agregar(vertices[i]);
            }
        }
        return retorno;
    }
   /* public boolean existeVertice(String vert) {
        return obtenerPosporString(vert) != -1;
    }*/
    
  /*private int obtenerPosporString(String vertice) {
        if (vertice != null) {
            for (int i = 0; i < topeAeropuertos; i++) {
               Aeropuerto a= vertices[i];
                if (a!=null && a.getCodigo().equals(vertice) ) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }
  public boolean existeArista(String origen, String destino) {
        int posOrigen = obtenerPosporString(origen);
        int posDestino = obtenerPosporString(destino);
        return matAdy[posOrigen][posDestino].isExiste();
    }*/
    
    public boolean agregarVueloenArista(Vuelo vuelo) {
        String codigoAeropuertoOrigen = vuelo.getCodigoAeropuertoOrigen();
        String codigoAeropuertoDestino = vuelo.getCodigoAeropuertoDestino();
        Aeropuerto aeropuertoOrigen = obtenerVertice(codigoAeropuertoOrigen);
        Aeropuerto aeropuertoDestino = obtenerVertice(codigoAeropuertoDestino);
        int posOrigen = obtenerPos(aeropuertoOrigen);
        int posDestino = obtenerPos(aeropuertoDestino);
        if(existeArista(aeropuertoOrigen, aeropuertoDestino)) {
            Arista arista = matAdy[posOrigen][posDestino];
            Lista<Vuelo> vuelos = arista.getVuelos();
            for (Vuelo v : vuelos) {
                if (v.getCodigoVuelo().equals(vuelo.getCodigoVuelo())) {
                    return false;
                }
            }
            arista.setVuelos(vuelo);
            return true;
        }
        return false;
        }
        /*int posOrigen = obtenerPosporString(vuelo.getCodigoAeropuertoOrigen());
        int posDestino = obtenerPosporString(vuelo.getCodigoAeropuertoDestino());
      Lista<Vuelo> v = matAdy[posOrigen][posDestino].getVuelos();
       for (Vuelo elvuelo:v){
           if(elvuelo.getCodigoVuelo().equals(vuelo.getCodigoVuelo())){
               return false;
           }
       }
            matAdy[posOrigen][posDestino].setVuelos(vuelo);
            return true;
    }*/
        public Lista<Aeropuerto> bfs(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea ) {
            Cola<Tupla> cola = new Cola<>();
            boolean[] visitados = new boolean[topeAeropuertos];
            Aeropuerto aeropuertoOrigen = obtenerVertice(codigoAeropuertoOrigen);
            int inicio = obtenerPos(aeropuertoOrigen);
            Lista<Aeropuerto> aeropuertosAlcanzables = new Lista<>();

            aeropuertosAlcanzables.agregarOrdenado(aeropuertoOrigen);

            visitados[inicio] = true;
            cola.encolar(new Tupla(inicio, 0));

            while (!cola.isEmpty()) {
                Tupla aux = cola.desencolar();
                int pos = aux.getPos();
                int nivel = aux.getNivel();

                if (nivel == cantidad) {
                    continue;
                }

                Aeropuerto aeropuertoActual = vertices[pos];
                for (int i = 0; i < topeAeropuertos; i++) {
                    if (matAdy[pos][i].isExiste() && !visitados[i]) {
                        visitados[i] = true;
                        Aeropuerto aeropuertoDestino = vertices[i];
                        aeropuertosAlcanzables.agregarOrdenado(aeropuertoDestino);
                        cola.encolar(new Tupla(i, nivel + 1));
                    }
                }
            }
            return aeropuertosAlcanzables;
        }
    }
