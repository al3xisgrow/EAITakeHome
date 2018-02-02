package main.java.server.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.shared.util.ISerializer;
import main.java.shared.util.Serializer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;



public class DefaultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        ISerializer serializer = new Serializer();
        serializer.sendResponse("Hmm, that won't work! \"/\" is an invalid URL!", httpExchange);

    }
}
