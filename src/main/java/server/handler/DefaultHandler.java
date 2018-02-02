package main.java.server.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Alexis on 2/1/18.
 */

public class DefaultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers respHeaders = httpExchange.getResponseHeaders();
        try {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } catch (IOException e) {
            System.out.println("Couldn't send response headers!");
        }

        OutputStreamWriter sw = new OutputStreamWriter(httpExchange.getResponseBody());

        String url = httpExchange.getRequestURI().getPath();
        String[] components = url.split("/");

        StringBuilder webpage = new StringBuilder();

        sw.write(webpage.toString());
        sw.close();


    }
}
