# Template Volks API Tests

Projeto de testes de API utilizando Java 8, Maven 3.9.11 e RestAssured com padrões Page Actions e Factory.

## Estrutura do Projeto

```
src/test/java/com/volks/api/
├── BaseTest.java                    # Configuração base dos testes
├── ExampleApiTest.java              # Exemplo de testes
├── actions/
│   ├── BaseAction.java              # Classe base para actions
│   └── PostAction.java              # Actions de Posts
└── factory/
    └── ActionFactory.java           # Factory para criar actions
```

## Requisitos

- Java 8
- Maven 3.9.11

## Como executar

> ⚠️ **Importante**: É necessário usar o perfil `-Pintegration-tests` para executar os testes, pois eles estão configurados como testes de integração.

```bash
# Executar todos os testes
mvn test -Pintegration-tests

# Executar um arquivo de teste específico
mvn test -Pintegration-tests -Dtest=ContractTest

# Executar múltiplos arquivos de teste
mvn test -Pintegration-tests -Dtest=ExampleApiTest,ContractTest

# Executar um método específico dentro de um arquivo
mvn test -Pintegration-tests -Dtest=ExampleApiTest#testMetodo

# Executar múltiplos métodos específicos
mvn test -Pintegration-tests -Dtest=ExampleApiTest#testMetodo1,ExampleApiTest#testMetodo2

# Executar com padrão (wildcard)
mvn test -Pintegration-tests -Dtest=*Test

# Executar com URL customizada
mvn test -Pintegration-tests -Dapi.base.uri=https://api.exemplo.com
```

## Como usar

### Criando um novo teste

```java
public class MeuTeste extends BaseTest {

    @Test
    public void testExemplo() {
        Response response = actions.post().getPostById(1);
        assertEquals(200, response.getStatusCode());
    }
}
```

### Adicionando uma nova Action

1. Crie a classe na pasta `actions/`:

```java
public class UserAction extends BaseAction {
    public UserAction(RequestSpecification requestSpec) {
        super(requestSpec);
    }
    
    public Response getUserById(int id) {
        return given()
                .spec(requestSpec)
                .when()
                .get("/users/" + id)
                .then()
                .extract()
                .response();
    }
}
```

2. Adicione o método na `ActionFactory`:

```java
public UserAction user() {
    return new UserAction(requestSpec);
}
```

3. Use nos testes:

```java
Response response = actions.user().getUserById(1);
```

## Dependências

- RestAssured 5.3.2
- JUnit 4.13.2

# volks-api-java8
