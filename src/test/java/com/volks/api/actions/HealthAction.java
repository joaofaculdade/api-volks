// Dentro de HealthAction (que herda de BaseAction)
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public Response getHealth(String authorization) {
    return given()
            .spec(requestSpecification)
            .relaxedHTTPSValidation()
            .header("Authorization", authorization)
        .when()
            .get("/api-cms-credito-pessoal/health")
        .then()
            .extract()
            .response();
}