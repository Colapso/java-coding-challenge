package app;//import com.github.jknack.handlebars.Handlebars;
//import com.github.jknack.handlebars.Template;



import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

import java.io.IOException;
import java.lang.module.Configuration;

public class ChallengeRouter {

    private ChallengeApi challengeApi;
    private final TemplateEngine engine = HandlebarsTemplateEngine.create();

    public ChallengeRouter(Vertx vertex, ChallengeApi challengeApi ) {
        this.challengeApi = challengeApi;

    }

    public static Router router(Vertx vertex) {
        return router(vertex, new ChallengeApi(new HttpRequest()));
    }

    public static Router router(Vertx vertx, ChallengeApi api) {
        Router router = Router.router(vertx);
        ChallengeRouter handlers = new ChallengeRouter(vertx, api);
        router.route("/home").handler(handlers::handleHome);

        return router;
    }

    private void handleHome(RoutingContext ctx) {
        HttpServerResponse resp = ctx.response();
        resp.putHeader("content-type", "text/html");

        engine.render(ctx, "src/main/resources/templates", "/home.hbs", view -> {
            if(view.succeeded())
                resp.end(view.result());
            else
                ctx.fail(view.cause());
        });

    }
}
