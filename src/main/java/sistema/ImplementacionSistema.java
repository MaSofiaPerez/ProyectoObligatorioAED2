package sistema;

import dominio.*;
import interfaz.*;
import TADs.*;

public class ImplementacionSistema implements Sistema {

    private ABB<Pasajero> arbolPasajeros;
    private ABB<Aerolinea> arbolAerolineas;
    private GrafoAeropuerto aeropuertosConexiones;
    private int maxAeropuertos;
    private int maxAerolineas;
    private int cantidadAerolineasRegistradas;
    private int cantidadAeropuertosRegistrados;
    private ABB<Aeropuerto> arbolAeropuertos;
    private ABB<Pasajero> arbolCategoriaPlatino;
    private ABB<Pasajero> arbolCategoriaFrecuente;
    private ABB<Pasajero> arbolCategoriaEstandar;


    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {

        if (maxAeropuertos <= 5) {
            return Retorno.error1("");
        } else if (maxAerolineas <= 3) {
            return Retorno.error2("");
        }
        this.maxAerolineas = maxAerolineas;
        this.maxAeropuertos = maxAeropuertos;
        arbolPasajeros = new ABB<Pasajero>();
        arbolAerolineas = new ABB<Aerolinea>();
        arbolCategoriaPlatino = new ABB<Pasajero>();
        arbolCategoriaFrecuente = new ABB<Pasajero>();
        arbolCategoriaEstandar = new ABB<Pasajero>();
        aeropuertosConexiones = new GrafoAeropuerto(maxAeropuertos, cantidadAeropuertosRegistrados);
        return Retorno.ok();

    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        Pasajero p = new Pasajero(cedula, nombre, telefono, categoria);
        if (!p.validarVacios(cedula, nombre, telefono, categoria)) {
            return Retorno.error1("");
        } else if (!p.validarCI(cedula)) {
            return Retorno.error2("");
        } else if (arbolPasajeros.existe(p)) {
            return Retorno.error3("");
        } else {
            arbolPasajeros.agregar(p);
            switch (p.getCategoria()) {
                case PLATINO:
                    arbolCategoriaPlatino.agregar(p);
                    break;
                case FRECUENTE:
                    arbolCategoriaFrecuente.agregar(p);
                    break;
                case ESTANDAR:
                    arbolCategoriaEstandar.agregar(p);
            }
            return Retorno.ok();
        }

    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        Pasajero p = new Pasajero(cedula, "", "", null);
        if (cedula == null || cedula.isEmpty()) {
            return Retorno.error1("");
        } else if (!p.validarCI(cedula)) {
            return Retorno.error2("");
        } else if (!arbolPasajeros.existe(p)) {
            return Retorno.error3("");
        } else {
            p = arbolPasajeros.obtener(p);
            String datosPasajero = p.toString();
            return Retorno.ok(arbolPasajeros.getRecorridos(), datosPasajero);
        }
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        StringBuilder mostrarLista = new StringBuilder();
        Lista<Pasajero> pasajeros = arbolPasajeros.listarAsc();
        for (int i = 0; i < pasajeros.largo(); i++) {
            Pasajero p = pasajeros.get(i);
            mostrarLista.append(p.toString());
            if (i < pasajeros.largo() - 1) {
                mostrarLista.append("|");
            }
        }
        return Retorno.ok(mostrarLista.toString());
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        StringBuilder mostrarLista = new StringBuilder();
        Lista<Pasajero> pasajeros = new Lista<Pasajero>();
        switch (categoria) {
            case PLATINO:
                pasajeros = arbolCategoriaPlatino.listarAsc();
                break;
            case FRECUENTE:
                pasajeros = arbolCategoriaFrecuente.listarAsc();
                break;
            case ESTANDAR:
                pasajeros = arbolCategoriaEstandar.listarAsc();
        }

        for (int i = 0; i < pasajeros.largo(); i++) {
            Pasajero p = pasajeros.get(i);
            mostrarLista.append(p.toString());
            if (i < pasajeros.largo() - 1) {
                mostrarLista.append("|");
            }
        }
        return Retorno.ok(mostrarLista.toString());
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {
        if (cantidadAerolineasRegistradas == maxAerolineas) {
            return Retorno.error1("");
        } else if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return Retorno.error2("");
        } else {
            Aerolinea aerolinea = new Aerolinea(codigo, nombre);
            if (arbolAerolineas.existe(aerolinea)) {
                return Retorno.error3("");
            } else {
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
            if (i < aerolineas.largo() - 1) {
                mostrarLista.append("|");
            }
        }
        return Retorno.ok(mostrarLista.toString());
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        Aeropuerto a = new Aeropuerto(codigo, nombre);
        if (cantidadAeropuertosRegistrados == maxAeropuertos) {
            return Retorno.error1("");
        } else if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return Retorno.error2("");
        } else if (aeropuertosConexiones.existeVertice(a)) {
            return Retorno.error3("");
        } else {
            aeropuertosConexiones.agregarVertice(a);
            cantidadAeropuertosRegistrados++;
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        if (kilometros <= 0) {
            return Retorno.error1("");
        } else if (codigoAeropuertoOrigen == null || codigoAeropuertoDestino == null || codigoAeropuertoDestino.isEmpty() || codigoAeropuertoOrigen.isEmpty()) {
            return Retorno.error2("");
        } else {
            Aeropuerto aOrigen = aeropuertosConexiones.obtenerVertice(codigoAeropuertoOrigen);
            Aeropuerto aDestino = aeropuertosConexiones.obtenerVertice(codigoAeropuertoDestino);
            if (!aeropuertosConexiones.existeVertice(aOrigen)) {
                return Retorno.error3("");
            } else if (!aeropuertosConexiones.existeVertice(aDestino)) {
                return Retorno.error4("");
            } else if (aeropuertosConexiones.existeArista(aOrigen, aDestino)) {
                return Retorno.error5("");
            } else {
                aeropuertosConexiones.agregarArista(aOrigen, aDestino, kilometros);
                return Retorno.ok();
            }
        }
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        boolean a=false;
        Vuelo v=new Vuelo(codigoAeropuertoOrigen,codigoAeropuertoDestino,codigoDeVuelo,combustible,minutos,costoEnDolares,codigoAerolinea);
        if(!v.validarDoubles(combustible, minutos, costoEnDolares)){
            return Retorno.error1("");
        } else if (!v.validarStrings(codigoAeropuertoOrigen,codigoAeropuertoDestino,codigoDeVuelo,codigoAerolinea)) {
            return Retorno.error2("");
        } if (!aeropuertosConexiones.existeVertice(codigoAeropuertoOrigen)) {
            return Retorno.error3("");
        } else if (!aeropuertosConexiones.existeVertice(codigoAeropuertoDestino)) {
            return Retorno.error4("");
        }
        Lista<Aerolinea> aerolineas = arbolAerolineas.listarDes();
        for (int i = 0; i < aerolineas.largo(); i++) {
            Aerolinea aerolinea = aerolineas.get(i);

            if (aerolinea.getCodigo().equals(codigoAerolinea)) {
              a=true;
              break;
            }}
        if(!a){
            return Retorno.error5("");
        }
          if (!aeropuertosConexiones.existeArista(codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
            return Retorno.error6("");
        }
          if(!aeropuertosConexiones.agregarVueloenArista(v)){
           return    Retorno.error7("");
          }
          return Retorno.ok();
    }
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
