package main.java.shared.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class Serializer implements ISerializer {

    private static Gson gson = new Gson();

    public Serializer() {}

    /**
     * Converts a String of JSON into an Object of the specified class
     * @param jsonObj InputStream containing the JSON data
     * @param c  class of the Object to retrieve from the String
     * @return Object, with the data from the String of JSON
     */
    public Object toObject(InputStream jsonObj, Class c) throws IOException {
        String json = readStream(jsonObj);
        return gson.fromJson(json, c);
    }


    /**
     * Reads an InputStream into a String
     * @param is InputStream to be read
     * @return convertedString
     * @throws IOException
     */
    public String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /**
     * Sends the response to an HTTP exchange with the given message
     * @param message Message to be included in the response
     * @param exchange HTTPExchange that is added to and sent
     */
    public void sendResponse(String message, HttpExchange exchange) {
        Object r = formatResponse("message", message);
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


    /**
     * Adds a property to a JSON object
     * @param property Property name
     * @param message The string representation of the value for the given property
     * @return
     */
    private JsonObject formatResponse(String property, String message) {
        JsonObject json = new JsonObject();
        json.addProperty(property, message);
        return json;
    }
}