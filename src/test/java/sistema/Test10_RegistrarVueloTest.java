package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10_RegistrarVueloTest {
    private Sistema sistema;
    private Retorno retorno;
    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6,10);
    }

    @Test
  void noDeberiaRegistrarVueloConParamsIgualesACero() {
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),0,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,0,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,0,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloConParamsMenoresACero() {
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),-1,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,-1,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,-1,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloConParamsVacios() {
        retorno = sistema.registrarVuelo(new String(""),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String(""),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String(""),10,10,10,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,10,new String(""));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloConParamsNull() {
        retorno = sistema.registrarVuelo(null,new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),null,new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),null,10,10,10,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,10,null);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiAeropuertoOrigenNoExiste() {
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_3,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("32"),new String("2"),new String("14"),140,103,145,new String("14"));
        assertEquals(Retorno.Resultado.ERROR_3,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("331"),new String("1"),new String("31"),1033,1044,13300,new String("31"));
        assertEquals(Retorno.Resultado.ERROR_3,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiAeropuertoDestinoNoExiste() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("4"),new String("22"),new String("33"),12,144,14,new String("12"));
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("4"),new String("52"),new String("55"),144,12,155,new String("14"));
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("6"),new String("12"),new String("77"),13,155,123,new String("17"));
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiAerolineaNoExiste() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_5,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("4"),new String("6"),new String("55"),144,12,155,new String("14"));
        assertEquals(Retorno.Resultado.ERROR_5,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("6"),new String("4"),new String("77"),13,155,123,new String("17"));
        assertEquals(Retorno.Resultado.ERROR_5,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiNoExisteConexion() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAerolinea("1", "Aerolinea 1");
        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_6,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarVueloSiYaExiste() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAeropuerto(new String("3"), new String("Aeropuerto3"));
        sistema.registrarAeropuerto(new String("7"), new String("Aeropuerto7"));
        sistema.registrarAerolinea("1", "Aerolinea 1");
        sistema.registrarAerolinea("2", "Aerolinea 2");
        sistema.registrarConexion(new String("1"), new String("2"),10);
        sistema.registrarConexion(new String("2"), new String("7"),23);
        sistema.registrarConexion(new String("3"), new String("1"),46);
        sistema.registrarConexion(new String("2"), new String("3"),12);

        sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        sistema.registrarVuelo(new String("2"),new String("7"),new String("654"),30,14,154,new String("1"));
        sistema.registrarVuelo(new String("3"),new String("1"),new String("22"),13,44,132,new String("2"));
        sistema.registrarVuelo(new String("2"),new String("3"),new String("33"),34,22,144,new String("2"));

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_7,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("2"),new String("7"),new String("654"),30,14,154,new String("1"));
        assertEquals(Retorno.Resultado.ERROR_7,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("3"),new String("1"),new String("22"),13,44,132,new String("2"));
        assertEquals(Retorno.Resultado.ERROR_7,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("2"),new String("3"),new String("33"),34,22,144,new String("2"));
        assertEquals(Retorno.Resultado.ERROR_7,retorno.getResultado());
    }

    @Test
    void deberiaRegistrarVuelo() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAeropuerto(new String("3"), new String("Aeropuerto3"));
        sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        sistema.registrarAeropuerto(new String("5"), new String("Aeropuerto5"));
        sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));
        sistema.registrarAeropuerto(new String("7"), new String("Aeropuerto7"));
        sistema.registrarAeropuerto(new String("8"), new String("Aeropuerto8"));

        sistema.registrarAerolinea("1", "Aerolinea 1");
        sistema.registrarAerolinea("2", "Aerolinea 2");
        sistema.registrarAerolinea("3", "Aerolinea 3");
        sistema.registrarAerolinea("4", "Aerolinea 4");

        sistema.registrarConexion(new String("1"), new String("2"),10);
        sistema.registrarConexion(new String("3"), new String("4"), 100);
        sistema.registrarConexion(new String("4"), new String("5"), 200);
        sistema.registrarConexion(new String("5"), new String("6"), 150);

        retorno = sistema.registrarVuelo(new String("1"),new String("2"),new String("1"),10,10,100,new String("1"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("4"), new String("5"), new String("55"), 100, 50, 200, new String("4"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("4"), new String("5"), new String("445"), 100, 30, 500, new String("4"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("4"), new String("5"), new String("5"), 250, 60, 700, new String("4"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("5"), new String("6"), new String("2"), 150, 60, 250, new String("2"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("3"), new String("4"), new String("3"), 120, 55, 220, new String("3"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("3"), new String("4"), new String("12"), 120, 55, 220, new String("4"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        retorno = sistema.registrarVuelo(new String("1"), new String("2"), new String("12"), 220, 15, 440, new String("4"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
    }
}
