package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test07_ListarAerolineasDescendenteTest {
    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);

        retorno = sistema.registrarAerolinea(new String("A"), new String("Aerolinea 1"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("B"), new String("Aerolinea 2"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("C"), new String("Aerolinea 3"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("D"), new String("Aerolinea 4"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = sistema.registrarAerolinea(new String("E"), new String("Aerolinea 5"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("F"), new String("Aerolinea 6"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("G"), new String("Aerolinea 7"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("H"), new String("Aerolinea 8"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("I"), new String("Aerolinea 9"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarAerolinea(new String("J"), new String("Aerolinea 10"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void listarDescendente() {
        retorno = sistema.listarAerolineasDescendente();
        String resEsperado = "J;Aerolinea 10|I;Aerolinea 9|H;Aerolinea 8|G;Aerolinea 7|F;Aerolinea 6|E;Aerolinea 5|D;Aerolinea 4|C;Aerolinea 3|B;Aerolinea 2|A;Aerolinea 1";
        assertEquals(resEsperado, retorno.getValorString());
    }

    @Test
    void listarAscendenteVacio() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);
        retorno = sistema.listarAerolineasDescendente();
        assertEquals("", retorno.getValorString());
    }
}
