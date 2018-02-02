package main.java.server.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;



/**
 * Created by Alexis on 2/1/18.
 */

public class CrudHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI urlInfo = httpExchange.getRequestURI();

        String url = urlInfo.toString();

        String[] urlComponents = url.split("/");


        if (httpExchange.getRequestMethod().toLowerCase().equals("get")) {
            JsonObject j = createResponse("Get!");
            sendResponse(j, httpExchange);
            //Serializer.fromJson(readStream(httpExchange.getRequestBody()), CreateRequest.class);
        } else if (httpExchange.getRequestMethod().toLowerCase().equals("post")){
            JsonObject j = createResponse("Post!");
            sendResponse(j, httpExchange);
        } else if (httpExchange.getRequestMethod().toLowerCase().equals("put")){
            JsonObject j = createResponse("Put!");
            sendResponse(j, httpExchange);
        } else if (httpExchange.getRequestMethod().toLowerCase().equals("delete")){
            JsonObject j = createResponse("Delete!");
            sendResponse(j, httpExchange);
        }
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private JsonObject createResponse(String message) {
        JsonObject json = new JsonObject();
        json.addProperty("message", message);
        return json;
    }

    private void sendResponse(Object r, HttpExchange exchange) {
        if (r == null) {
            // fail!
            System.out.println("Send Response Failed!");
        } else {
            try {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStreamWriter response = new OutputStreamWriter(exchange.getResponseBody());
                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                response.write(gson.toJson(r));
                response.close();

            } catch (IOException e) {
                System.out.println("Couldn't send response!");
                e.printStackTrace();
            }
        }
    }
    
}
