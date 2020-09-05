package io.github.wesleyosantos91.resource;

import io.github.wesleyosantos91.MysqlTestLifecycleManager;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.swagger.model.RequestPostPessoa;
import io.swagger.model.RequestPutPessoa;
import org.apache.http.HttpStatus;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(MysqlTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class PessoaResourceTest {

    private RequestPostPessoa requestPostPessoa;

    private RequestPutPessoa requestPutPessoa;

    @BeforeEach
    void setUp() throws Exception {

        requestPostPessoa = new RequestPostPessoa();
        requestPostPessoa.setNome("Gabriel Ducati Teixeira");
        requestPostPessoa.setCpf("10275977340");
        requestPostPessoa.setDataNascimento(LocalDate.of(1995, 10, 10));
        requestPostPessoa.setEmail("gabriel.teixeira@uol.com.br");

        requestPutPessoa = new RequestPutPessoa();
        requestPutPessoa.setNome("Guilherme Machado Gomes");
        requestPutPessoa.setDataNascimento(LocalDate.of(1972, 9, 12));
        requestPutPessoa.setEmail("guilherme.gomes@uol.com.br");
    }

    @Test
    @DisplayName("Deve retorna uma pessoa pelo seu código")
    @Order(1)
    void consultarPeloCodigo() {
        String resultado = given()
                .pathParam("codigo", 1L)
                .get("/pessoas/{codigo}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();
        Approvals.verifyJson(resultado);

    }

    @Test
    @DisplayName("Deve lançar ObjectNotFoundException")
    @Order(2)
    void consultarPeloCodigoException() {
        given()
                .pathParam("codigo", 2L)
                .get("/pessoas/{codigo}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    @DisplayName("deve retornar todos umas lista de pessoas")
    @Order(3)
    void consultarTodos() {
        given()
                .get("/pessoas")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(1));
    }

    @Test
    @DisplayName("Deve alterar a o objeto pessoa")
    @Order(4)
    void alterarPessoa() {
        String resultado = given()
                .pathParam("codigo", 1L)
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(requestPutPessoa)
                .when()
                .put("/pessoas/{codigo}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();
        Approvals.verifyJson(resultado);

    }

    @Test
    @DisplayName("Deve cadastrar uma pessoa")
    @Order(5)
    void cadastrarPessoa() {
        String resultado = given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(requestPostPessoa)
                .when()
                .post("/pessoas")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().asString();

        Approvals.verifyJson(resultado);


    }

    @Test
    @DisplayName("Deve excluir uma pessoa pelo seu código")
    @Order(6)
    void excluirPessoa() {
        given()
                .pathParam("codigo", 1L)
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(requestPostPessoa)
                .when()
                .delete("/pessoas/{codigo}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("eve lançar ObjectNotFoundException ao tentar excluir uma pessoa pelo seu código")
    @Order(7)
    void excluirPessoaException() {
        given()
                .pathParam("codigo", 1L)
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(requestPostPessoa)
                .when()
                .delete("/pessoas/{codigo}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Deve lançar ObjectNotFoundException ao tentar alterar a o objeto pessoa")
    @Order(8)
    void alterarPessoaException() {
        given()
                .pathParam("codigo", 1L)
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(requestPutPessoa)
                .when()
                .put("/pessoas/{codigo}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }
}