package app;//import com.github.jknack.handlebars.Handlebars;

import dto.JsonResponseDTO;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

public class ChallengeRouter {

    private ChallengeApi challengeApi;
    private final TemplateEngine engine = HandlebarsTemplateEngine.create();

    public ChallengeRouter(Vertx vertex, ChallengeApi challengeApi ) {
        this.challengeApi = challengeApi;
    }

    public static Router router(Vertx vertex) {
        return router(vertex, new ChallengeApi(new HttpReq()));
    }

    public static Router router(Vertx vertx, ChallengeApi api) {
        Router router = Router.router(vertx);
        ChallengeRouter handlers = new ChallengeRouter(vertx, api);
        router.route("/home").handler(handlers::handleHome);
        //should be done with ajax request only actualize future table
        router.route("/home/translate").handler(handlers::handleTranslationRequest);
        router.route("/home/translate/check").handler(handlers::handleTranslationCheckRequest);

        return router;
    }

    private void handleTranslationCheckRequest(RoutingContext contex) {
        HttpServerRequest req = contex.request();
        String uid = req.getParam("uid");
        HttpServerResponse resp = contex.response();
        resp.putHeader("content-type", "text/html");

        JsonResponseDTO dto = challengeApi.getTranslationCheckRequest(uid);

        contex.put("sourceLanguage", dto.getSourceLanguage());
        contex.put("originalText", dto.getText());
        contex.put("targetLanguage", dto.getTarguetLanguage());
        contex.put("translatedText", dto.getTranslatedText());
        contex.put("status", dto.getStatus());
        contex.put("uid", dto.getUid());

        if(dto.getStatus().equals("completed")) {

            engine.render(contex, "src/main/resources/templates", "/home.hbs", view -> {
                if (view.succeeded())
                    resp.end(view.result());
                else
                    contex.fail(view.cause());
            });
        }
    }

    private void handleTranslationRequest(RoutingContext contex) {
        HttpServerRequest req = contex.request();
        String textToTranslate = req.getParam("textToTranslate");
        String fromLang = req.getParam("fromLang");
        String toLang = req.getParam("toLang");
        HttpServerResponse resp = contex.response();
        resp.putHeader("content-type", "text/html");

        JsonResponseDTO dto = challengeApi.getTranslationRequest(textToTranslate, fromLang, toLang);

        contex.put("sourceLanguage", dto.getSourceLanguage());
        contex.put("originalText", dto.getText());
        contex.put("targetLanguage", dto.getTarguetLanguage());
        contex.put("translatedText", dto.getTranslatedText());
        contex.put("status", dto.getStatus());
        contex.put("uid", dto.getUid());


        engine.render(contex, "src/main/resources/templates", "/home.hbs",view -> {
            if(view.succeeded())
                resp.end(view.result());
            else
                contex.fail(view.cause());
        });
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
