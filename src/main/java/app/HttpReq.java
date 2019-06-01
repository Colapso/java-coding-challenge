package app;

import dto.TranslationRequestDtos.JsonRequestDto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Objects.nonNull;

public class HttpReq {

    public String sendPost(String httpMethod, String authHeader, String uri, JsonRequestDto dto) {
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();
        try {
            //Create connection
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authHeader);
            connection.setDoOutput(true);

            //send request
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            if (nonNull(dto)) {
                wr.write(dto.toString());
            }
            wr.flush();
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            is.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return response.toString();
        }
    }

        public String sendGet(String httpMethod,String authHeader,String uri){

            URL obj = null;
            StringBuffer response = null;
            try {
                obj = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                // optional default is GET
                con.setRequestMethod(httpMethod);
                con.setRequestProperty("Authorization", authHeader);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));

                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString();

        }

    //Async not working

   /* public CompletableFuture<String> postContentAsync(String authHeader,String uri, String textToTranslate) {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        String obj = "{\"text\" : \"Hello, world!\", \"source_language\" : \"en\", \"target_language\" : \"pt\", \"text_format\" : \"text\"}";

        return asyncHttpClient
                .preparePost("https://api.unbabel.com/tapi/v2/tone/")
                .setHeader("Content-Type", "application/json")
                .addHeader("Authorization", authHeader)
                .execute() // ASYNC => não bloqueante
                .toCompletableFuture()                 // CF<Response>
                .thenApply(Response::getResponseBody)  // CF<String>
                .whenComplete((res, ex) -> closeAHC(asyncHttpClient)); // CF<String>
    }


    public CompletableFuture<String> getContentAsync(String authHeader,String uri, String textToTranslate) {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        String obj = "{\"text\" : \"Hello, world!\", \"source_language\" : \"en\", \"target_language\" : \"pt\", \"text_format\" : \"text\"}";

        return asyncHttpClient
                .prepareGet("https://api.unbabel.com/tapi/v2/tone/")
                .setHeader("Content-Type", "application/json")
                .addHeader("Authorization", authHeader)
                .execute() // ASYNC => não bloqueante
                .toCompletableFuture()                 // CF<Response>
                .thenApply(Response::getResponseBody)  // CF<String>
                .whenComplete((res, ex) -> closeAHC(asyncHttpClient)); // CF<String>
    }
    static void closeAHC(AsyncHttpClient client){
        try {
            client.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}

