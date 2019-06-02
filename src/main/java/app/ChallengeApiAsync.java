package app;

import com.google.gson.Gson;
import dto.LangPairsDtos.LangPairObjectsContainerDto;
import dto.TranslationRequestDtos.JsonResponseDto;

import java.util.concurrent.CompletableFuture;

public class ChallengeApiAsync {

    private HttpReq request;
    private Gson gson = new Gson();

    private static String HOME = "https://sandbox.unbabel.com/tapi/v2";
    private static String POST_TRANSLATION = "/translation/";
    private static String LANGUAGE_PAIR = "/language_pair/";
    private static String API_USER = "ApiKey fullstack-challenge:";
    private static String API_PASS = "9db71b322d43a6ac0f681784ebdcc6409bb83359";

    public ChallengeApiAsync(HttpReq request) {
        this.request = request;
    }

    //Get translation
    public CompletableFuture<JsonResponseDto> getTranslation(String textToTranslate) {
        return getRequestAsync(textToTranslate, JsonResponseDto.class);
    }

    //Get supported languages
    public CompletableFuture<LangPairObjectsContainerDto> getLanguagePair() {
        return getRequestAsync(HOME + LANGUAGE_PAIR, LangPairObjectsContainerDto.class);
    }

    //GET request async
    public <T> CompletableFuture<T> getRequestAsync(String url, Class<T> klass) {
        return request
                .getContentAsync(API_USER + API_PASS, url)
                .thenApply(elem -> gson.fromJson(elem, klass));
    }

    //POST request async
    public <T> CompletableFuture<T> postRequestAsync(String textToTranslate, Class<T> klass) {
        return request.postContentAsync(API_USER + API_PASS, HOME + POST_TRANSLATION, textToTranslate)
                .thenApply(elem -> gson.fromJson(elem, klass));
    }
}

