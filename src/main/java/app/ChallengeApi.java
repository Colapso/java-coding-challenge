package app;

import com.google.gson.Gson;
import dto.JsonRequestDTO;
import dto.JsonResponseDTO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class ChallengeApi {

    private HttpReq request;
    private Gson gson = new Gson();

    private static String HOME = "https://sandbox.unbabel.com/tapi/v2";
    private static String POST_TRANSLATION = "/translation/";

    private static String API_USER = "ApiKey fullstack-challenge:";
    private static String API_PASS = "9db71b322d43a6ac0f681784ebdcc6409bb83359";

    public ChallengeApi(HttpReq request) {
        this.request = request;
    }

    public JsonResponseDTO getTranslationRequest(String textToTranslate, String fromLang, String toLang){
        return gson.fromJson(request.postContent("POST",API_USER+API_PASS, HOME + POST_TRANSLATION, new JsonRequestDTO(textToTranslate, fromLang, toLang, "text")), JsonResponseDTO.class);
    }

    public JsonResponseDTO getTranslationCheckRequest(String uid){
        return gson.fromJson(request.sendGet("GET",API_USER+API_PASS, HOME + POST_TRANSLATION + uid + "/", null), JsonResponseDTO.class);
    }




    //Async prepared
   /* public CompletableFuture<JsonResponseDTO> getTranslation(String textToTranslate){
        return getRequest(textToTranslate, JsonResponseDTO.class);
    }


    public <T> CompletableFuture<T> getRequest(String textToTranslate, Class<T> klass)
    {
        return request
                .getContent(API_USER + API_PASS, HOME + POST_TRANSLATION, textToTranslate)
                .thenApply(elem -> gson.fromJson(elem, klass));
    }


    public CompletableFuture<JsonRequestDTO> postTranslation(String textToTranslate){
        return postRequest(textToTranslate, JsonRequestDTO.class);
    }*/

    /*public <T> CompletableFuture<T> postRequest(String textToTranslate, Class<T> klass)
    {
        return request
                .postContent(API_USER + API_PASS, HOME + POST_TRANSLATION)
                .thenApply(elem -> gson.fromJson(elem, klass));
    }*/

}
