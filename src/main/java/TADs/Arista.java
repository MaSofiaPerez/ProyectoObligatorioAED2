package TADs;

import dominio.Vuelo;

public class Arista {

    private boolean existe;
    private double pesoEnKm;
    private double pesoEnMin;
    private Lista<Vuelo> vuelos;

    public Arista() {
        this.existe = false;
        this.vuelos = new Lista<>();
    }

    public Arista(double pesoEnKm, double pesoEnMin) {
        this.existe = true;
        this.pesoEnKm = pesoEnKm;
        this.pesoEnMin = pesoEnMin;
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

    public double getPesoEnMin() {
        return pesoEnMin;
    }
    public void setPesoEnMin(double pesoEnMin){ this.pesoEnMin = pesoEnMin;}

    public double getPesoEnKm() {
        return pesoEnKm;
    }

    public void setPesoEnKm(double pesoEnKm) {
        this.pesoEnKm = pesoEnKm;
    }
}
