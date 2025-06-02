package org.infnet;

import io.javalin.Javalin;

public class App2 {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        app.before(ctx -> {
            long inicio = System.currentTimeMillis();
            ctx.attribute("inicio", inicio);
            ctx.attribute("retorno", "Teste");
            System.out.println("Antes da Rota: " + ctx.path());
        });
        app.after(context -> {
            long inicio = context.attribute("inicio");
            long end = System.currentTimeMillis();
            long duracao = end- inicio;
            System.out.println("Após a rota: " + context.path() +  " Demorou: " + duracao + "ms");


        });

        app.get("/inicio", ctx ->{
            System.out.println("Metodo Inicio ");
            ctx.result("Inicio");
        });

        app.start(8082);

    }
}
