package TADs;

import dominio.Vuelo;

public class Arista {

    private boolean existe;
    private double peso;
    private Lista<Vuelo> vuelos;

    public Arista() {
        this.existe = false;
        this.peso = peso;
         this.vuelos = new Lista<>();
    }

    public Arista(double unPeso) {
        this.existe = true;
        this.peso = unPeso;
    }
public Lista<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(Vuelo vuelo) {
        this.vuelos.agregar(vuelo) ;
    }
    public boolean isExiste() {
        return existe;
    }
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
