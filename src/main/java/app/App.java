package app;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class App {

    private static String HOME = "https://sandbox.unbabel.com/tapi/v2/";
    private static String POST_TRANSLATION = "/translation/";

    private static String API_USER = "fullstack-challenge";
    private static String API_PASS = "9db71b322d43a6ac0f681784ebdcc6409bb83359";

    public static void main(String[] args) throws Exception  {
        System.out.println("ola");
        Vertx vertx = Vertx.vertx();
        Router router = ChallengeRouter.router(vertx);

        vertx
          .createHttpServer()
          .requestHandler(router::accept)
         .listen(8080);
    }
}
