package com.volks.api.factory;

import com.volks.api.actions.HealthAction;
import com.volks.api.actions.ContractAction;
import com.volks.api.actions.SimulacaoAction;
import io.restassured.specification.RequestSpecification;

public class ActionFactory {

    private RequestSpecification requestSpec;

    public ActionFactory(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public HealthAction health() {
        return new HealthAction(requestSpec);
    }

    public ContractAction contract() {
        return new ContractAction(requestSpec);
    }

    public SimulacaoAction simulacao() {
        return new SimulacaoAction(requestSpec);
    }

    public HealthAction health() {
        return new HealthAction(requestSpec);
    }
}

