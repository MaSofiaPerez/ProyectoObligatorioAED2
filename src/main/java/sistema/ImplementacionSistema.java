package sistema;

import dominio.*;
import interfaz.*;
import TADs.*;
public class ImplementacionSistema implements Sistema {

    private ABB<Pasajero> arbolPasajeros;
    private ABB<Categoria> pasajerosEstandares;
    private ABB<Categoria> pasajerosFrencuentes;
    private ABB<Categoria> pasajerosPlatinos;
    private ABB<Aerolinea> arbolAerolineas;
    private ABB<Aeropuerto> arbolAeropuertos;
    private int maxAeropuertos;
    private int maxAerolineas;
    private int cantidadAerolineasRegistradas;


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
        arbolAerolineas=new ABB<Aerolinea>();
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
            String datosPasajero = p.toString();
            return Retorno.ok(arbolPasajeros.getRecorridos(), datosPasajero);
        }
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        StringBuilder mostrarLista= new StringBuilder();
        Lista<Pasajero> pasajeros = arbolPasajeros.listarAsc();
        for (int i = 0; i < pasajeros.largo(); i++) {
            Pasajero p = pasajeros.get(i);
            mostrarLista.append(p.toString());
            if(i<pasajeros.largo() - 1){
                mostrarLista.append("|");
            }
        }
        return Retorno.ok(mostrarLista.toString());
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {
         if(cantidadAerolineasRegistradas == maxAerolineas){
            return Retorno.error1(""); 
        } else if (codigo==null || codigo.isEmpty() || nombre==null || nombre.isEmpty()){
            return  Retorno.error2("");
        }else{
             Aerolinea aerolinea = new Aerolinea(codigo,nombre);
             if (arbolAerolineas.existe(aerolinea)){
                 return Retorno.error3("");
             }else {
                 arbolAerolineas.agregar(aerolinea);
                 cantidadAerolineasRegistradas++;
                 return Retorno.ok();
             }
         }
    }

    @Override
    public Retorno listarAerolineasDescendente() {
        StringBuilder mostrarLista = new StringBuilder();
        Lista<Aerolinea> aerolineas = arbolAerolineas.listarDes();
        for (int i = 0; i < aerolineas.largo(); i++) {
            Aerolinea aerolinea = aerolineas.get(i);
            mostrarLista.append(aerolinea.toString());
            if(i<aerolineas.largo() - 1){
                mostrarLista.append("|");
            }
        }
        return Retorno.ok(mostrarLista.toString());
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
