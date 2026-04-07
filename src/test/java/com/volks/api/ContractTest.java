package com.volks.api;

import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContractTest extends BaseTest {

    @Test
    public void testHealthCreditoPessoal() {
        Response response = actions.health().checkHealthCreditoPessoal();
        assertEquals(200, response.getStatusCode());
        assertEquals("Healthy", response.jsonPath().getString("status"));
        assertEquals("Healthy", response.jsonPath().getString("entries.self.status"));
    }

    @Test
    public void testGetContractsByCpfWithTwoContracts() {
        String cpf = "52282046013";
        Response response = actions.contract().getContractsByCpf(cpf);
        
        assertEquals(200, response.getStatusCode());
        assertEquals(2, response.jsonPath().getList("$").size());
        
        // Validar primeiro contrato
        assertEquals(1, response.jsonPath().getInt("[0].contractCode"));
        assertEquals("Ativo", response.jsonPath().getString("[0].contractStatus"));
        assertEquals("EMPRESTIMO CONSIGNADO", response.jsonPath().getString("[0].productName"));
        assertEquals(4195.6, response.jsonPath().getDouble("[0].outstandingAmount"), 0.01);
        
        // Validar segundo contrato
        assertEquals(11, response.jsonPath().getInt("[1].contractCode"));
        assertEquals("Ativo", response.jsonPath().getString("[1].contractStatus"));
        assertEquals("EMPRESTIMO CONSIGNADO", response.jsonPath().getString("[1].productName"));
        assertEquals(143201.73, response.jsonPath().getDouble("[1].outstandingAmount"), 0.01);
    }

    @Test
    public void testGetContractsByCpfWithOneContract() {
        String cpf = "39694137020";
        Response response = actions.contract().getContractsByCpf(cpf);
        
        assertEquals(200, response.getStatusCode());
        assertEquals(1, response.jsonPath().getList("$").size());
        
        // Validar único contrato
        assertEquals(12, response.jsonPath().getInt("[0].contractCode"));
        assertEquals("Cancelado", response.jsonPath().getString("[0].contractStatus"));
        assertEquals("EMPRESTIMO CONSIGNADO", response.jsonPath().getString("[0].productName"));
        assertEquals(0.0, response.jsonPath().getDouble("[0].outstandingAmount"), 0.01);
        assertEquals(1, response.jsonPath().getInt("[0].totalNumberOfInstallments"));
        assertEquals("Pgto Cancelado", response.jsonPath().getString("[0].disbursementStatus"));
    }

    @Test
    public void testGetContractsByCpfWithNoContracts() {
        String cpf = "98181513053";
        Response response = actions.contract().getContractsByCpf(cpf);
        
        assertEquals(404, response.getStatusCode());
        //assertEquals(0, response.jsonPath().getList("$").size());
    }

    @Test
    public void testGetContractsByCpfNotRegistered() {
        String cpf = "50489756859";
        Response response = actions.contract().getContractsByCpf(cpf);

        assertEquals(404, response.getStatusCode());
        //assertEquals(0, response.jsonPath().getList("$").size());
    }

    @Test
    public void testGetContractsByCpfInvalidCpfExpect500() {
        String cpf = "123456";
        Response response = actions.contract().getContractsByCpf(cpf);

        assertEquals(500, response.getStatusCode());
        assertEquals("An error occurred", response.jsonPath().getString("title"));
        assertEquals(500, response.jsonPath().getInt("status"));
        assertEquals("Dados inv\u00E1lidos", response.jsonPath().getString("detail"));
    }
}
