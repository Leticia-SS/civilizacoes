package org.infnet.config;

import io.javalin.Javalin;

public class AppConfig {
    public static Javalin criarApp(Integer porta) {
        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new GsonMapper());
        }).start(porta);
        return app;
    }
}
