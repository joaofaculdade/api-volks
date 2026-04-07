package com.volks.api;

import com.volks.api.factory.ActionFactory;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseTest {

    protected ActionFactory actions;

    @Before
    public void setUp() {
        String baseUri = System.getProperty("api.base.uri", "https://jsonplaceholder.typicode.com");
        RestAssured.baseURI = baseUri;
        RequestSpecification requestSpec = RestAssured.given();
        actions = new ActionFactory(requestSpec);
    }
}
