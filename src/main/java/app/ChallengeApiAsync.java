package app;

import com.google.gson.Gson;

public class ChallengeApiAsync {

    private HttpReq request;
    private Gson gson = new Gson();

    private static String HOME = "https://sandbox.unbabel.com/tapi/v2";
    private static String POST_TRANSLATION = "/translation/";

    private static String API_USER = "ApiKey fullstack-challenge:";
    private static String API_PASS = "9db71b322d43a6ac0f681784ebdcc6409bb83359";

    public ChallengeApiAsync(HttpReq request) {
        this.request = request;
    }

    //Async prepared
    /*public CompletableFuture<JsonResponseDto> getTranslation(String textToTranslate) {
        return getRequestAsync(textToTranslate, JsonResponseDto.class);
    }


    public <T> CompletableFuture<T> getRequestAsync(String uid, Class<T> klass) {
        return request
                .sendGetAsync(API_USER + API_PASS, HOME + POST_TRANSLATION + uid + "/")
                .thenApply(elem -> gson.fromJson(elem, klass));
    }


    public <T> CompletableFuture<T> postRequestAsync(String textToTranslate, Class<T> klass) {
        return request.sendPostAsync(API_USER + API_PASS, HOME + POST_TRANSLATION, textToTranslate)
                .thenApply(elem -> gson.fromJson(elem, klass));
    }*/
}

