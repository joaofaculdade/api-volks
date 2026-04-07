package com.volks.api.actions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ContractAction extends BaseAction {

    public ContractAction(RequestSpecification requestSpec) {
        super(requestSpec);
    }

    public Response getContractsByCpf(String cpf) {
        return given()
                .relaxedHTTPSValidation()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36 Edg/144.0.0.0")
                .header("Authorization", "123mudar")
                .header("Referer", "")
                .when()
                .get("https://app.dev.cms.cld.bancovw.internal/api-cms-credito-pessoal/v1/contratos/cliente/" + cpf)
                .then()
                .extract()
                .response();
    }
}
