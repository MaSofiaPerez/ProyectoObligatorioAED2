package sistema;

import dominio.*;
import interfaz.*;
import TADs.*;
public class ImplementacionSistema implements Sistema {

    private ABB<Pasajero> arbolPasajeros;
    private ABB<Aerolinea> arbolAerolineas;
    private int maxAeropuertos;
    private int maxAerolineas;


    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {

        if(maxAeropuertos<=5){
            return Retorno.error1("");
        }else if (maxAerolineas<=3){
            return Retorno.error2("");
        }
        this.maxAerolineas = maxAerolineas;
        this.maxAeropuertos = maxAeropuertos;
        arbolPasajeros = new ABB<Pasajero>();
        return Retorno.ok();

    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        Pasajero p = new Pasajero(cedula, nombre, telefono, categoria);
        if(!p.validarVacios(cedula, nombre, telefono, categoria)){
            return Retorno.error1("");
        } else if (!p.validarCI(cedula)) {
            return Retorno.error2("");
        } else if (arbolPasajeros.existe(p)) {
            return Retorno.error3("");
        }else {
            arbolPasajeros.agregar(p);
            return Retorno.ok();
        }

    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        Pasajero p = new Pasajero(cedula, "", "", null);
        if(cedula == null || cedula.isEmpty()){
            return Retorno.error1("");
        } else if (!p.validarCI(cedula)) {
            return Retorno.error2("");
        } else if (!arbolPasajeros.existe(p)){
            return Retorno.error3("");
        }else {
            p = arbolPasajeros.obtener(p);
            //falta corregir la devolucion de elementos que recorrio
            return Retorno.ok(0, p.toString());
        }
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarAerolineasDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoEnMinutos(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return Retorno.noImplementada();
    }


}
