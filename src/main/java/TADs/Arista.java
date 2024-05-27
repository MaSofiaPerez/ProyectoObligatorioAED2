package TADs;

import dominio.Vuelo;

public class Arista {

    private boolean existe;
    private double pesoEnKm;
    private Lista<Vuelo> vuelos;

    public Arista() {
        this.existe = false;
        this.vuelos = new Lista<>();
    }

    public Arista(double pesoEnKm) {
        this.existe = true;
        this.pesoEnKm = pesoEnKm;

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
        double minDuracion = Double.POSITIVE_INFINITY;
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getMinutos() < minDuracion) {
                minDuracion = vuelo.getMinutos();
            }
        }
        return minDuracion == Double.POSITIVE_INFINITY ? 0 : minDuracion;
    }

    public double getPesoEnKm() {
        return pesoEnKm;
    }

    public void setPesoEnKm(double pesoEnKm) {
        this.pesoEnKm = pesoEnKm;
    }
}
