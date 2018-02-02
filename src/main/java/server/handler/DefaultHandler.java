package main.java.server.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;



public class DefaultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Responder r = new Responder();

        r.sendResponse("Hmm, that won't work! \"/\" is an Invalid URL!", httpExchange);
    }
}
