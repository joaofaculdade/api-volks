package com.volks.api.actions;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

@Test
public void deveRetornarHealthComStatus200EStatusesHealthy() {
    // Arrange
    String authorization = "123mudar";

    // Act
    Response response = actions.health().getHealth(authorization);

    // Assert
    assertEquals(200, response.getStatusCode());

    // Campos determinísticos do payload
    assertEquals("Healthy", response.jsonPath().getString("status"));
    assertEquals("Healthy", response.jsonPath().getString("entries.Memory.status"));
    assertEquals("Allocated megabytes in memory: 6 mb",
                 response.jsonPath().getString("entries.Memory.description"));
    assertEquals("Healthy", response.jsonPath().getString("entries.self.status"));
    assertEquals("OK", response.jsonPath().getString("entries.self.description"));

    // Campos variáveis (apenas existência / não vazios)
    String totalDuration = response.jsonPath().getString("totalDuration");
    assertNotNull(totalDuration);
    // opcionalmente, poderia checar não vazio:
    // assertTrue(totalDuration != null && !totalDuration.isEmpty());
}