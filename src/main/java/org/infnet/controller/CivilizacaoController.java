package org.infnet.controller;

import com.google.gson.Gson;
import io.javalin.Javalin;
import org.infnet.exception.AcessoInvalidoException;
import org.infnet.modelo.Civilizacao;
import org.infnet.repository.CivilizacaoRepositoryInMemory;
import org.infnet.service.CivilizacaoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CivilizacaoController {
    public static void registrarFilters(Javalin app){
        app.before("/civilizacao/protegidas", ctx ->{
            // ctx.status(401).result(new ErrorResponse("Não Autorizado", "Error!").toString());
            String authorization = ctx.header("Authorization");
            //boolean valido = usuarioService.validarToken(authorization);
            //if(authorization == null || !valido) {
//                throw new AcessoInvalidoException("Usuario nao autenticado");
//            }else if (valido) {
//                System.out.println("Loguei");
//            }
        });
    }
    public static void registrarRotas(Javalin app){
        Gson gson = new Gson();

        CivilizacaoService civilizacaoService =
                new CivilizacaoService(new CivilizacaoRepositoryInMemory());

        app.get("/civilizacao", ctx ->{
            String termo = ctx.queryParam("localizacao");
            List<Civilizacao> resultado = (termo == null || termo.isBlank())
                    ? civilizacaoService.buscarTodas()
                    : civilizacaoService.buscarPorLocalizacao(termo);

            ctx.header("Content-Type","application/json");

            ctx.json(resultado);
            //Jackson
            //ctx.result(result);
        });

        app.get("/civilizacao/protegidas", ctx ->{
            System.out.println("Rodei");
            ctx.json(Map.of("message", "Nao era para estar vendo isso!"));
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
            ctx.status(201).result(sucesso("Civilizacao Cadastrada com Sucesso!"));
        });
        //DELETE /civilizacao/{id} -> civilizacao/1 civilizacao/dasda
        app.delete("/civilizacao/{id}", ctx ->{
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean ok = civilizacaoService.remover(id);
            ctx.header("Content-Type","application/json");

            if(ok){
                ctx.status(200).result(sucesso("Civilizacao Removida com Sucesso!"));
            }
        });
        //PUT  civilizacao/{id}
        app.put("/civilizacao/{id}", ctx-> {

            int id = Integer.parseInt(ctx.pathParam("id"));
            String body = ctx.body();
            Civilizacao nova = gson.fromJson(body, Civilizacao.class);
            boolean ok = civilizacaoService.atualizar(id, nova);
            ctx.header("Content-Type","application/json");

            if(ok){
                ctx.status(200).result(sucesso("Civilização atualizada com sucesso!"));
            }else {
                ctx.status(404).result(notFound());
            }

        });
    }
    private static String notFound(){
        Map<String, String> map = new HashMap<>();
        map.put("message", "Civilizacao nao encontrada!");
        Gson gson = new Gson();
        return gson.toJson(map);
    }
    private static String error(String message){
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        Gson gson = new Gson();
        return gson.toJson(map);
    }
    private static String sucesso(String message){
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
