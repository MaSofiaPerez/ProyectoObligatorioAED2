package TADs;

import dominio.Aeropuerto;

public class Tupla {
    private int pos;
    private int nivel;
    private double costo;
    private Aeropuerto[] anterior;

    public Tupla(int pos, int nivel) {
        this.pos = pos;
        this.nivel = nivel;
    }

    public Tupla(double costo, Aeropuerto[] anterior) {
        this.costo = costo;
        this.anterior = anterior;

    }

    public int getPos() {
        return pos;
    }

    public int getNivel() {
        return nivel;
    }
    public double getCosto(){ return costo;}
    public Aeropuerto[] getAnterior(){ return anterior;}
}
