package org.infnet;

import com.google.gson.Gson;
import io.javalin.Javalin;
import org.infnet.modelo.Civilizacao;
import org.infnet.repository.CivilizacaoRepository;
import org.infnet.service.CivilizacaoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Gson gson = new Gson();
        CivilizacaoService civilizacaoService =
                new CivilizacaoService(new CivilizacaoRepository());
        Javalin app = Javalin.create().start(8081);
        //GET /civilizacao
        app.get("/civilizacao", ctx ->{
            String s = ctx.queryParam("nome");
            System.out.println("Query Param:  " + s);
            List<Civilizacao> civilizacoes = civilizacaoService.buscarTodas();
            ctx.header("Content-Type","application/json");
            String result = gson.toJson(civilizacoes);
            ctx.result(result);
        });

        //GET /civilizacao/id
        app.get("/civilizacao/{id}", ctx ->{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Civilizacao civilizacao = civilizacaoService.buscarPorId(id);
            ctx.header("Content-Type","application/json");
            if(civilizacao == null){
                ctx.status(404).result(notFound());
            }else {
                String result = gson.toJson(civilizacao);
                ctx.result(result);
            }

        });

        //POST /civilizacao -> Criar uma nova
        app.post("/civilizacao", ctx ->{
            String body = ctx.body();
            Civilizacao civilizacao = gson.fromJson(body, Civilizacao.class);
            civilizacaoService.criar(civilizacao);
            ctx.status(201).result(created());
        });


    }
    private static String notFound(){
        Map<String, String> map = new HashMap<>();
        map.put("message", "Civilizacao nao encontrada!");
        Gson gson = new Gson();
        return gson.toJson(map);
    }
    private static String created(){
        Map<String, String> map = new HashMap<>();
        map.put("message", "Civilizacao Cadastrada com Sucesso!");
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
