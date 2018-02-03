package server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import shared.util.ISerializer;
import shared.util.Serializer;


public class DefaultHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        ISerializer serializer = new Serializer();
        serializer.sendResponse("Hmm, that won't work! \"/\" is an invalid URL!", httpExchange);

    }
}
