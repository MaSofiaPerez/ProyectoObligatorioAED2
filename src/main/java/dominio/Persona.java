package dominio;

import interfaz.Categoria;
import TADs.*;

import java.util.regex.Pattern;

public class Persona {

    //Atributos
    private String cedula;
    private int cedulaInt;
    private String nombre;
    private String telefono;
    private Categoria categoria;

    //Ctor
    public Persona(String cedula, String nombre, String telefono, Categoria categoria){
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.categoria = categoria;

    }

    //Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getCedulaInt() {
        return cedulaInt;
    }

    public void setCedulaInt(int cedulaInt) {
        this.cedulaInt = cedulaInt;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean validarCI(String cedula){
        if(cedula == null || cedula.isEmpty()){
            return false;
        }

        cedula = cedula.replaceAll("\\.", "").replaceAll("-", "");

        if(cedula.length() != 11 && cedula.length() != 10){
            return false;
        }

        char primerDigito = cedula.charAt(0);
        if(primerDigito < '1' || primerDigito > '9'){
            return false;
        }

        for(int i = 1; i < cedula.length(); i++){
            if(!Character.isDigit(cedula.charAt(i))){
                return false;
            }
        }

        return true;
    }

    public void convertirCI(String cedula){
        cedula = cedula.replaceAll("\\.", "").replaceAll("-", "");
        int cedulaInt = Integer.parseInt(cedula);
    }






}
