package app;//import com.github.jknack.handlebars.Handlebars;

import dto.LangPairsDtos.LangPairObjectsContainerDto;
import dto.TranslationRequestDtos.JsonResponseDto;
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

    public ChallengeRouter(ChallengeApi challengeApi) {
        this.challengeApi = challengeApi;
    }

    public static Router router(Vertx vertex) {
        return router(vertex, new ChallengeApi(new HttpReq()));
    }

    //Define routes and handlers for each one
    public static Router router(Vertx vertx, ChallengeApi api) {
        Router router = Router.router(vertx);
        ChallengeRouter handlers = new ChallengeRouter(api);
        router.route("/home").handler(handlers::handleHome);
        router.route("/home/translate").handler(handlers::handleTranslationRequest);
        router.route("/home/translate/check").handler(handlers::handleTranslationCheckRequest);

        return router;
    }

    //Handler for firt request
    private void handleHome(RoutingContext context) {
        HttpServerRequest req = context.request();
        HttpServerResponse resp = context.response();
        resp.putHeader("content-type", "text/html");

        //Representation of resource that comes from server already mapped into dto
        LangPairObjectsContainerDto dto = challengeApi.getLanguagePair();

        //Add data of dto needed in context
        context.put("langPairContainer", dto.getObjects());

        // Render template
        engine.render(context, "src/main/resources/templates", "/home.hbs", view -> {
            if (view.succeeded())
                resp.end(view.result());
            else
                context.fail(view.cause());
        });
    }

    //Handler for translation request
    private void handleTranslationRequest(RoutingContext context) {
        HttpServerRequest req = context.request();
        String textToTranslate = req.getParam("textToTranslate");
        String langPair = req.getParam("langPair");
        String fromLang = langPair.split("/")[0].trim();
        String toLang = langPair.split("/")[1].trim();
        HttpServerResponse resp = context.response();
        resp.putHeader("content-type", "text/html");

        //Representation of resource that comes from server already mapped into dto
        JsonResponseDto dto = challengeApi.getTranslation(textToTranslate, fromLang, toLang);

        context.put("sourceLanguage", dto.getSourceLanguage());
        context.put("originalText", dto.getText());
        context.put("targetLanguage", dto.getTarguetLanguage());
        context.put("translatedText", dto.getTranslatedText());
        context.put("status", dto.getStatus());
        context.put("uid", dto.getUid());

        // Render template
        engine.render(context, "src/main/resources/templates", "/table.hbs", view -> {
            if (view.succeeded())
                resp.end(view.result());
            else
                context.fail(view.cause());
        });
    }

    //Handler for check translation request
    private void handleTranslationCheckRequest(RoutingContext context) {
        HttpServerRequest req = context.request();
        String uid = req.getParam("uid");
        HttpServerResponse resp = context.response();
        resp.putHeader("content-type", "text/html");

        //Representation of resource that comes from server already mapped into dto
        JsonResponseDto dto = challengeApi.getTranslationCheck(uid);

        //Add data of dto needed in context
        context.put("sourceLanguage", dto.getSourceLanguage());
        context.put("originalText", dto.getText());
        context.put("targetLanguage", dto.getTarguetLanguage());
        context.put("translatedText", dto.getTranslatedText());
        context.put("status", dto.getStatus());
        context.put("uid", dto.getUid());

        // Render template
        engine.render(context, "src/main/resources/templates", "/table.hbs", view -> {
            if (view.succeeded())
                resp.end(view.result());
            else
                context.fail(view.cause());
        });

    }
}
