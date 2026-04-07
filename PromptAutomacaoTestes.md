
# Prompt para Automação de Testes

Este prompt facilita a criação de novos testes automatizados na estrutura atual do projeto. Basta informar o comando curl e o response esperado, e você receberá:
- Caminho e nome do arquivo de teste a ser criado/adicionado
- Código Java do teste
- Código da action correspondente (se necessário)

---

## Como usar

Informe:
1. O comando curl da requisição a ser testada
2. O response esperado (JSON ou texto)

### Exemplo de uso
```
curl: curl -X GET "http://localhost:8080/api/health"
response: {
  "status": "UP"
}
```

---

## Retorno esperado

1. **Caminho e nome do arquivo de teste:**
   - src/test/java/com/volks/api/HealthTest.java

2. **Código do teste:**
```java
// ...existing imports...
public class HealthTest extends BaseTest {
    @Test
    public void testHealthStatusUp() {
        String response = HealthAction.getHealth();
        assertTrue(response.contains("\"status\": \"UP\""));
    }
}
```

3. **Código da action (se necessário):**
   - src/test/java/com/volks/api/actions/HealthAction.java
```java
// ...existing imports...
public class HealthAction extends BaseAction {
    public static String getHealth() {
        return given()
            .when()
            .get("/api/health")
            .then()
            .extract()
            .asString();
    }
}
```

---

Adapte para outros endpoints informando o curl e o response esperado.
Compartilhe este prompt com o time para automatizar a criação de testes.
