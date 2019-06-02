package app;

import com.google.gson.Gson;
import dto.LangPairsDtos.LangPairObjectsContainerDto;
import dto.TranslationRequestDtos.JsonRequestDto;
import dto.TranslationRequestDtos.JsonResponseDto;

public class ChallengeApi {

    private HttpReq request;
    private Gson gson = new Gson();

    private static String HOME = "https://sandbox.unbabel.com/tapi/v2";
    private static String TRANSLATION = "/translation/";
    private static String LANGUAGE_PAIR = "/language_pair/";
    private static String API_USER = "ApiKey fullstack-challenge:";
    private static String API_PASS = "9db71b322d43a6ac0f681784ebdcc6409bb83359";

    public ChallengeApi(HttpReq request) {
        this.request = request;
    }

    //Check status of asked translation
    public JsonResponseDto getTranslationCheck(String uid) {
        return gson.fromJson(request.sendGet("GET", API_USER + API_PASS, HOME + TRANSLATION + uid + "/"), JsonResponseDto.class);
    }

    //Get translation
    public JsonResponseDto getTranslation(String textToTranslate, String fromLang, String toLang) {
        return gson.fromJson(request.sendPost("POST", API_USER + API_PASS, HOME + TRANSLATION, new JsonRequestDto(textToTranslate, fromLang, toLang, "text")), JsonResponseDto.class);
    }

    //Get supported languages
    public LangPairObjectsContainerDto getLanguagePair() {
        return gson.fromJson(request.sendGet("GET", API_USER + API_PASS, HOME + LANGUAGE_PAIR), LangPairObjectsContainerDto.class);
    }
}
