package dominio;

public class Vuelo {
    //Atributos
    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private String codigoVuelo;
    private double combustible;
    private double minutos;
    private double costoEnDolares;
    private String codigoAerolinea;

    //Ctor
    public Vuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea){
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.codigoVuelo = codigoVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
        this.codigoAerolinea = codigoAerolinea;
    }

    //Getters y Setters
    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public void setCodigoAeropuertoOrigen(String codigoAeropuertoOrigen) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getMinutos() {
        return minutos;
    }

    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public void setCodigoAerolinea(String codigoAerolinea) {
        this.codigoAerolinea = codigoAerolinea;
    }

    public boolean validarDoubles(double combustible, double minutos, double costoEnDolares){
        if(combustible <= 0 || minutos <= 0 || costoEnDolares <= 0){
            return false;
        }
        return true;
    }

    public boolean validarStrings(String codigoAeropuertoDeOrigen, String codigoAeropuertoDestino, String codigoVuelo, String codigoAerolinea){
        return codigoAeropuertoDeOrigen != null && !codigoAeropuertoDeOrigen.isEmpty() && codigoAeropuertoDestino != null && !codigoAeropuertoDestino.isEmpty() && codigoVuelo != null && !codigoVuelo.isEmpty() && codigoAerolinea != null && !codigoAerolinea.isEmpty();
    }
}
