package org.infnet.controller;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.restassured.path.json.JsonPath;
import org.infnet.config.AppConfig;
import org.infnet.config.GlobalHandler;
import org.infnet.modelo.Civilizacao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;


public class CivilizacaoControllerTest {
    private static Javalin app;
    @BeforeAll
    public static void iniciarServidor(){
        app = AppConfig.criarApp(8080);
        CivilizacaoController.registrarRotas(app);
        CivilizacaoController.registrarFilters(app);
        GlobalHandler.registrar(app);
    }
    @AfterAll
    public static void pararServidor(){
        app.stop();
    }
    @Test
    public void deveListarTodasAsCivilizacoes(){
            when()
                    .get("/civilizacao")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("size()",greaterThan(0))
                    .body("[0].nome", equalTo("Egito Antigo"))
                    .body("nome",hasItems("Egito Antigo","Maias"));

    }
    @Test
    public void deveListarTodasAsCivilizacoesComFiltro(){
        when()
                .get("/civilizacao?localizacao=Mesoamérica")
                .then()
                .log().all()
                .statusCode(200)
                .body("", hasSize(is(1)))
                .body("size()", equalTo(1))
                .body("[0].id", not(nullValue()));

    }
//Rest Assured
//Hamcrest
}
