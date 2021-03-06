package app;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.Router;

public class App {

    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(options);
        Router router = ChallengeRouter.router(vertx);

        //Server creation
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }
}
