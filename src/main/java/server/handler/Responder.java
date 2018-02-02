package main.java.server.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class Responder {

    public void sendResponse(String message, HttpExchange exchange) {
        Object r = createResponse(message);
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


    private JsonObject createResponse(String message) {
        JsonObject json = new JsonObject();
        json.addProperty("message", message);
        return json;
    }
}
