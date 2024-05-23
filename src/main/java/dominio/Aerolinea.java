package dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Aerolinea implements Comparable<Aerolinea> {

    //Atributos
    private String codigo;
    private String nombre;

    //Ctor
    public Aerolinea(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    //Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Aerolinea otraAerolinea) {
        return this.codigo.compareTo(otraAerolinea.getCodigo());
    }

    @Override
    public String toString(){
        return codigo + ";" + nombre;
    }
}
