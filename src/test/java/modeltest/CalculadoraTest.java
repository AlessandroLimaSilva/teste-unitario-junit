package modeltest;
import model.Calculadora;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Copyright (c) 2021 everis Brasil under MIT License
 *
 * Testes unitários para a classe Calculadora da package calculadorasimples
 *
 * Observem que não estamos nem perto de ter uma cobertura de testes adequada
 * com estes exemplos abaixo. O nosso objetivo aqui é ilustrar a utilização do
 * JUnit, e buscamos fazer os testes suficientes para isto.
 */

//Podemos executar os teste atraves do terminal
//no diretorio do projeto:
//mvn clean compile test
public class CalculadoraTest {
    static Calculadora calculadora;

    //@Anotação indicando que este é um metodo de teste
    @Test
    //@Disabled //desabilita o teste no qual foi colocada a annotation
    public void testaSomaMenosIngenuo() {
        Calculadora myCalc = new Calculadora();

        boolean temErro = false;
        if (myCalc.soma(2, 2) != 4) {
            temErro = true;
        }
        if (myCalc.soma(2, -2) != 0) {
            temErro = true;
        }
        if (myCalc.soma(-2, 2) != 0) {
            temErro = true;
        }
        if (myCalc.soma(-2, -2) != -4) {
            temErro = true;
        }
        if (myCalc.soma(0, 0) != 0) {
            temErro = true;
        }
        if (temErro) {
            //Metodo de indicação de falha
            fail("Houve um erro na validação da soma.");
        }
    }

    //@Anotação indicando que este é um metodo de teste
    @Test
    public void deveResultarQuatroAoSomarDoisEDois() {
        // Calculadora calculadora = new Calculadora();
        assertEquals(4.0, calculadora.soma(2, 2));
    }

    //@Anotação indicando que este é um metodo de teste
    @Test
    public void deveResultarZeroAoSomarDoisEMenosDois() {
        // Calculadora calculadora = new Calculadora();
        assertEquals(0.0, calculadora.soma(2, -2));
        //assertEquals() recebe o valor esperado e o valor obtido para comparar
    }

    //@BeforeAll Executa este metodo antes dos teste
    @BeforeAll
    public static void setup() {
        calculadora = new Calculadora();
    }
    //@BeforeEach executa antes de cada testes
    // @AfterEach executa apos cada testes
    // @AfterAll executa apos todos os testes


    //Da um nome ao teste e mostra o nome ao executar o teste
    @DisplayName("Valida múltiplas somas com informações em CSV")
    //Possibilita passar varios valores para efetuar testes no metodo possibilitando
    //Uma maior quantidade de testes e uma melhor qualidade no codigo
    @ParameterizedTest
    //Passa os valores para serem validados no metodo
    //Neste caso 1+1=2 || 2+3=5 ||valor,valor, resultado esperado
    @CsvSource({ "1.0, 1.0, 2.0", "2.0, 3.0, 5.0" })
    void validaMultiplasSomasCSV(double parcela1, double parcela2, double resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.soma(parcela1, parcela2));
    }

    //Da um nome ao teste e mostra o nome ao executar o teste
    @DisplayName("Valida múltiplas somas com informações em arquivo CSV")
    //Possibilita passar varios valores para efetuar testes no metodo possibilitando
    //Uma maior quantidade de testes e uma melhor qualidade no codigo
    @ParameterizedTest
    //Posibilita passar um csv contendo dados para efetuar os testes
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void validaMultiplasSomasArqCSV(double parcela1, double parcela2, double resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.soma(parcela1, parcela2));
    }

    //@Anotação indicando que este é um metodo de teste
    @Test
    public void testaExcecao() {
        //Teste se ocorre uma execption
        assertThrows(ArithmeticException.class, () -> {
            int retorno = 4 / 0;
            System.out.println(retorno);
        });
    }

}