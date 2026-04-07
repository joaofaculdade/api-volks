package com.volks.api;

import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimulacaoTest extends BaseTest {

    @Test
    public void testSimulacaoParcelas() {
        String body = "{\n" +
                "    \"totalParcelas\":6,\n" +
                "    \"valorSolicitado\":300 ,\n" +
                "    \"cnpj\": \"3495672000103\",\n" +
                "    \"produto\": \"EMPRESTIMO_CONSIGNADO\",\n" +
                "    \"dataPrimeiroPagamento\": \"2026-01-07\"\n" +
                "}";

        Response response = actions.simulacao().postSimulacoesParcelas("123mudar", body);

        assertEquals(200, response.getStatusCode());
        assertEquals(6, response.jsonPath().getInt("totalParcelas"));
        assertEquals(56.61f, response.jsonPath().getFloat("valorParcela"), 0.5f);
    }

    @Test
    public void testSimulacaoParcelas_AcimaDe60() {
        String body = "{\n" +
                "    \"totalParcelas\":144,\n" +
                "    \"valorSolicitado\":300 ,\n" +
                "    \"cnpj\": \"3495672000103\",\n" +
                "    \"produto\": \"EMPRESTIMO_CONSIGNADO\",\n" +
                "    \"dataPrimeiroPagamento\": \"2026-01-07\"\n" +
                "}";
        Response response = actions.simulacao().postSimulacoesParcelas("123mudar", body);
        assertEquals(422, response.getStatusCode());
        assertEquals("Quantidade de parcelas maior ao permitido: 60", response.jsonPath().getString("detail"));
    }

    @Test
    public void testSimulacaoParcelas_ParcelaZero() {
        String body = "{\n" +
                "    \"totalParcelas\":0,\n" +
                "    \"valorSolicitado\":300 ,\n" +
                "    \"cnpj\": \"3495672000103\",\n" +
                "    \"produto\": \"EMPRESTIMO_CONSIGNADO\",\n" +
                "    \"dataPrimeiroPagamento\": \"2026-01-07\"\n" +
                "}";
        Response response = actions.simulacao().postSimulacoesParcelas("123mudar", body);
        assertEquals(422, response.getStatusCode());
        assertEquals("Dados obrigatorios nao informados. -   qtdeParcelas", response.jsonPath().getString("detail"));
    }

    @Test
    public void testSimulacaoParcelas_ValorMenorQueMinimo() {
        String body = "{\n" +
                "    \"totalParcelas\":60,\n" +
                "    \"valorSolicitado\":299 ,\n" +
                "    \"cnpj\": \"3495672000103\",\n" +
                "    \"produto\": \"EMPRESTIMO_CONSIGNADO\",\n" +
                "    \"dataPrimeiroPagamento\": \"2026-01-07\"\n" +
                "}";
        Response response = actions.simulacao().postSimulacoesParcelas("123mudar", body);
        assertEquals(422, response.getStatusCode());
    }

    @Test
    public void testSimulacaoParcelas_ValorMaiorQueMaximo() {
        String body = "{\n" +
                "    \"totalParcelas\":60,\n" +
                "    \"valorSolicitado\":1000000000,\n" +
                "    \"cnpj\": \"3495672000103\",\n" +
                "    \"produto\": \"EMPRESTIMO_CONSIGNADO\",\n" +
                "    \"dataPrimeiroPagamento\": \"2026-01-07\"\n" +
                "}";
        Response response = actions.simulacao().postSimulacoesParcelas("123mudar", body);
        assertEquals(422, response.getStatusCode());
    }

    @Test
    public void testSimulacaoParcelas_CnpjInvalido() {
        String body = "{\n" +
                "    \"totalParcelas\":2,\n" +
                "    \"valorSolicitado\":100000,\n" +
                "    \"cnpj\": \"03495672000103\",\n" +
                "    \"produto\": \"EMPRESTIMO_CONSIGNADO\",\n" +
                "    \"dataPrimeiroPagamento\": \"2026-01-07\"\n" +
                "}";
        Response response = actions.simulacao().postSimulacoesParcelas("123mudar", body);
        assertEquals(412, response.getStatusCode());
    }

    @Test
    public void testSimulacaoParcelas_ProdutoIncorreto() {
        String body = "{\n" +
                "    \"totalParcelas\":2,\n" +
                "    \"valorSolicitado\":100000,\n" +
                "    \"cnpj\": \"3495672000103\",\n" +
                "    \"produto\": \"EMPRESTIMO_CONSIGNADO1\",\n" +
                "    \"dataPrimeiroPagamento\": \"2026-01-07\"\n" +
                "}";
        Response response = actions.simulacao().postSimulacoesParcelas("123mudar", body);
        assertEquals(400, response.getStatusCode());
    }
}
