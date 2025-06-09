package org.infnet.config;

import io.javalin.Javalin;
import org.infnet.dto.ErrorResponse;
import org.infnet.exception.AcessoInvalidoException;
import org.infnet.exception.CivilizacaoInvalidaException;

public class GlobalHandler {
    public static void registrar(Javalin app){
        app.exception(AcessoInvalidoException.class, (e, ctx) -> {
            String message = e.getMessage();
            ctx.status(401).json(new ErrorResponse(message,e.getMessage()));

        });
        app.exception(CivilizacaoInvalidaException.class, (e, ctx) -> {
            String message = e.getMessage();
            ctx.status(400).json(new ErrorResponse(message,"Erro Ao criar civilizacao"));

        });
        app.exception(NumberFormatException.class, (e, ctx) -> {
            ctx.status(400).json(new ErrorResponse("Requisicao Invalida","Erro Ao criar civilizacao"));
        });
    }
}
