package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Retorno.Resultado;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05_ListarPasajerosPorCategoriaTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
     void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10,10);

        sistema.registrarPasajero(new String("4.685.375-3"), new String("Juliana"),  new String("1234"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("5.135.139-2"), new String("Gaston"),  new String("3456"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("5.888.365-4"), new String("Alejandra"),  new String("5634"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("5.447.365-1"), new String("Gustavo"),  new String("23456"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("1.111.111-1"), new String("Ana"), new String("1111"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("2.222.222-2"), new String("Carlos"), new String("2222"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("4.444.444-4"), new String("Dario"), new String("4444"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("6.666.666-6"), new String("Elena"), new String("6666"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("8.888.888-8"), new String("Gabriela"), new String("8888"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("9.999.999-9"), new String("Hugo"), new String("9999"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("1.234.567-8"), new String("Javier"), new String("1234"), Categoria.ESTANDAR);
    }

    @Test
    void deberiaListarJugadoresPorTipo() {
        retorno = sistema.listarPasajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Resultado.OK, retorno.getResultado());
        assertEquals("1.111.111-1;Ana;1111;Estándar|1.234.567-8;Javier;1234;Estándar|4.444.444-4;Dario;4444;Estándar|4.685.375-3;Juliana;1234;Estándar|5.135.139-2;Gaston;3456;Estándar|8.888.888-8;Gabriela;8888;Estándar", retorno.getValorString());

        retorno = sistema.listarPasajerosPorCategoria(Categoria.PLATINO);
        assertEquals(Resultado.OK, retorno.getResultado());
        assertEquals("2.222.222-2;Carlos;2222;Platino|5.447.365-1;Gustavo;23456;Platino|5.888.365-4;Alejandra;5634;Platino|6.666.666-6;Elena;6666;Platino|9.999.999-9;Hugo;9999;Platino", retorno.getValorString());


    }

    @Test
    void deberiaListarJugadoresPorTipoVacio(){
        retorno = sistema.listarPasajerosPorCategoria(Categoria.FRECUENTE);
        assertEquals(Resultado.OK,retorno.getResultado());
        assertEquals("",retorno.getValorString());
    }
}
