package dominio;

import interfaz.Categoria;

public class Pasajero implements Comparable<Pasajero> {

    //Atributos
    private String cedula;
    private int cedulaInt;
    private String nombre;
    private String telefono;
    private Categoria categoria;

    //Ctor
    public Pasajero(String cedula, String nombre, String telefono, Categoria categoria){
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

        // Patrón para el formato N.NNN.NNN-N o NNN.NNN-N
        String patron = "^[1-9]\\d?\\d?\\.\\d{3}\\.\\d{3}-\\d$|^[1-9]\\d{2}\\.\\d{3}-\\d$";

        // Verificar si la cédula coincide con el patrón
        return cedula.matches(patron);
    }

    public void convertirCI(String cedula){
        cedula = cedula.replaceAll("\\.", "").replaceAll("-", "");
        int cedulaInt = Integer.parseInt(cedula);
    }

    public boolean validarVacios(String cedula, String nombre, String telefono, Categoria categoria){
        return cedula != null && !cedula.isEmpty() && nombre != null && !nombre.isEmpty() && telefono != null && !telefono.isEmpty() && categoria != null;
    }


    @Override
    public int compareTo(Pasajero otroPasajero) {
        return this.cedula.compareTo(otroPasajero.cedula);
    }






}
