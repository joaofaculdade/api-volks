package com.volks.api.actions;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseAction {

    protected RequestSpecification requestSpec;

    public BaseAction(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    protected RequestSpecification given() {
        return RequestSpecification.class.cast(io.restassured.RestAssured.given());
    }
}
