package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test12_ViajeCostoMinimoKilometrosTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10, 10);
    }

  private void cargarSetAeropuertosYConexiones1() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAeropuerto(new String("3"), new String("Aeropuerto3"));
        sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        sistema.registrarAeropuerto(new String("5"), new String("Aeropuerto5"));
        sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));
        sistema.registrarAeropuerto(new String("7"), new String("Aeropuerto7"));
        sistema.registrarAeropuerto(new String("8"), new String("Aeropuerto8"));

        sistema.registrarConexion(new String("1"), new String("2"), 10);
        sistema.registrarConexion(new String("1"), new String("3"), 2);
        sistema.registrarConexion(new String("1"), new String("6"), 14);
        sistema.registrarConexion(new String("2"), new String("4"), 15);
        sistema.registrarConexion(new String("3"), new String("4"), 15);
        sistema.registrarConexion(new String("3"), new String("6"), 1);
        sistema.registrarConexion(new String("3"), new String("5"), 3);
        sistema.registrarConexion(new String("3"), new String("7"), 12);
        sistema.registrarConexion(new String("2"), new String("7"), 14);
        sistema.registrarConexion(new String("7"), new String("8"), 6);
        sistema.registrarConexion(new String("2"), new String("8"), 8);
        sistema.registrarConexion(new String("3"), new String("8"), 5);
        sistema.registrarConexion(new String("1"), new String("7"), 18);
        sistema.registrarConexion(new String("6"), new String("4"), 8);
        sistema.registrarConexion(new String("4"), new String("5"), 4);
        sistema.registrarConexion(new String("4"), new String("7"), 10);
        sistema.registrarConexion(new String("5"), new String("8"), 9);


        sistema.registrarAerolinea("1","Aerolinea1");
        sistema.registrarAerolinea("2","Aerolinea2");

        retorno = sistema.registrarVuelo("1", "2", "1", 100, 10, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("2", "4", "2", 100, 10, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarVuelo("1", "3", "3", 100, 15, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("3", "4", "4", 100, 30, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarVuelo("1", "6", "5", 100, 30, 10, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("6", "4", "6", 100, 30, 11, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarVuelo("2", "7", "34", 150, 42, 60, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("3", "7", "22", 140, 30, 40, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


        retorno = sistema.registrarVuelo("2", "8", "423", 180, 50, 70, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarVuelo("3", "8", "65", 150, 40, 51, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


        retorno = sistema.registrarVuelo("4", "5", "12", 40, 12, 20, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarVuelo("4", "7", "67", 120, 20, 50, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarVuelo("5", "8", "57", 170, 30, 40, "1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    }

    @Test
    void noDeberiaDevolverCostoMinimoParamVacio() {
        retorno = sistema.viajeCostoMinimoKilometros(new String(""), new String("4"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.viajeCostoMinimoKilometros(new String("1"), new String(""));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoParamNull() {
        retorno = sistema.viajeCostoMinimoKilometros(null, new String("4"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.viajeCostoMinimoKilometros(new String("1"), null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoNoExisteOrigen() {
        cargarSetAeropuertosYConexiones1();
        retorno = sistema.viajeCostoMinimoKilometros(new String("10"), new String("4"));
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoNoExisteDestino() {
        cargarSetAeropuertosYConexiones1();
        retorno = sistema.viajeCostoMinimoKilometros(new String("1"), new String("40"));
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void deberiaDevolverCostoMinimo() {
        cargarSetAeropuertosYConexiones1();
        retorno = sistema.viajeCostoMinimoKilometros(new String("1"), new String("8"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(7, retorno.getValorInteger());
        assertEquals("1;Aeropuerto1|3;Aeropuerto3|8;Aeropuerto8", retorno.getValorString());
    }

    @Test
    void deberiaDevolverCostoMinimo1() {
        cargarSetAeropuertosYConexiones1();
        sistema.registrarConexion(new String("5"), new String("4"), 8);
        sistema.registrarVuelo("5","4","7",10,2,45,"1");
        sistema.registrarVuelo("3","5","8",10,7,45,"1");

        retorno = sistema.viajeCostoMinimoKilometros(new String("1"), new String("4"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(11, retorno.getValorInteger());
        assertEquals("1;Aeropuerto1|3;Aeropuerto3|6;Aeropuerto6|4;Aeropuerto4", retorno.getValorString());
    }

    @Test
    void deberiaDevolverCostoMinimo2() {
        cargarSetAeropuertosYConexiones1();
        retorno = sistema.viajeCostoMinimoKilometros(new String("1"), new String("7"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(14, retorno.getValorInteger());
        assertEquals("1;Aeropuerto1|3;Aeropuerto3|7;Aeropuerto7", retorno.getValorString());
    }

    @Test
    void deberiaDevolverCostoMinimo3() {
        cargarSetAeropuertosYConexiones1();
        retorno = sistema.viajeCostoMinimoKilometros(new String("2"), new String("7"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(14, retorno.getValorInteger());
        assertEquals("2;Aeropuerto2|7;Aeropuerto7", retorno.getValorString());
    }

    @Test
    void nodeberiaDevolverCostoMinimoNoHayCamino() {
        cargarSetAeropuertosYConexiones1();
        retorno = sistema.viajeCostoMinimoKilometros(new String("5"), new String("6"));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    @Test
    void deberiaDevolverCostoMinimo4() {
        cargarSetAeropuertosYConexiones1();
        sistema.registrarConexion(new String("8"), new String("4"), 1);
        sistema.registrarVuelo("8","1","7",10,2,45,"1");
        sistema.registrarVuelo("2","8","8",10,7,45,"1");

        retorno = sistema.viajeCostoMinimoKilometros(new String("1"), new String("8"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(7, retorno.getValorInteger());
        assertEquals("1;Aeropuerto1|3;Aeropuerto3|8;Aeropuerto8", retorno.getValorString());
    }
}
