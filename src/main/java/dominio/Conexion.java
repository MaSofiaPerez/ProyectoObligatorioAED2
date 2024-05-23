package dominio;

public class Conexion {

    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private double kilometro;
    public Conexion(String codigoAeroOrigen, String codigoAeroDestino,
                    double kilometros) {
        this.codigoAeropuertoDestino = codigoAeroDestino;
        this.codigoAeropuertoOrigen = codigoAeroOrigen;
        this.kilometro = kilometros;
    }

    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public void setCodigoAeropuertoOrigen(String codigoAeropuertoOrigen) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
    }

    public double getKilometro() {
        return kilometro;
    }

    public void setKilometro(double kilometro) {
        this.kilometro = kilometro;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
    }

}
