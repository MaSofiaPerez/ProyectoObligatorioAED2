package TADs;

import dominio.Vuelo;

public class Arista {

    private boolean existe;
    private double peso;

    public Arista() {
        this.existe = false;
        this.peso = peso;
    }

    public Arista(double unPeso) {
        this.existe = true;
        this.peso = unPeso;
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
