package com.volks.api.actions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SimulacaoAction extends BaseAction {

    public SimulacaoAction(RequestSpecification requestSpec) {
        super(requestSpec);
    }

    public Response postSimulacoesParcelas(String authorization, String body) {
        return given()
            .relaxedHTTPSValidation()
            .spec(requestSpec)
            .header("authorization", authorization)
            .header("content-type", "application/json")
            .body(body)
            .when()
            .post("https://app.dev.cms.cld.bancovw.internal/api-cms-credito-pessoal/v1/simulacoes/parcelas")
            .then()
            .extract()
            .response();
    }
}
