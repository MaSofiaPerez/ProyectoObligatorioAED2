package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Retorno.Resultado;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04_ListarPasajerosAscendenteTest {
    Sistema sistema;
    Retorno retorno;

    @BeforeEach
   void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);

        sistema.registrarPasajero(new String("4.685.375-3"), new String("Juliana"),  new String("1234"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("5.135.139-2"), new String("Gaston"),  new String("3456"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("5.888.365-4"), new String("Alejandra"),  new String("5634"), Categoria.FRECUENTE);
        sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"),  new String("23456"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("3.456.789-0"), new String("Ana"), new String("7890"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("2.345.678-9"), new String("Carlos"), new String("6789"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("1.234.567-8"), new String("Beatriz"), new String("5678"), Categoria.FRECUENTE);
        sistema.registrarPasajero(new String("6.789.012-3"), new String("Dario"), new String("9012"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("7.890.123-4"), new String("Elena"), new String("0123"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("8.901.234-5"), new String("Fabian"), new String("1234"), Categoria.FRECUENTE);
        sistema.registrarPasajero(new String("9.012.345-6"), new String("Gabriela"), new String("2345"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("1.345.678-9"), new String("Ines"), new String("4567"), Categoria.FRECUENTE);
        sistema.registrarPasajero(new String("2.456.789-0"), new String("Javier"), new String("5678"), Categoria.ESTANDAR);
    }

    @Test
    void listarAscendente() {
        retorno = sistema.listarPasajerosAscendente();
        String resEsperado = "1.234.567-8;Beatriz;5678;Frecuente|1.345.678-9;Ines;4567;Frecuente|2.345.678-9;Carlos;6789;Platino|2.456.789-0;Javier;5678;Estándar|3.456.789-0;Ana;7890;Estándar|4.685.375-3;Juliana;1234;Estándar|5.135.139-2;Gaston;3456;Platino|5.447.365-1;Gustavo;23456;Estándar|5.888.365-4;Alejandra;5634;Frecuente|6.789.012-3;Dario;9012;Estándar|7.890.123-4;Elena;0123;Platino|8.901.234-5;Fabian;1234;Frecuente|9.012.345-6;Gabriela;2345;Estándar";
        assertEquals(Resultado.OK, retorno.getResultado());
        assertEquals(resEsperado, retorno.getValorString());
    }

    @Test
    void listarAscendenteVacio() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);
        retorno = sistema.listarPasajerosAscendente();
        assertEquals(Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }
}
