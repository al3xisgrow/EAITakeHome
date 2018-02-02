package main.java.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.server.dataAccess.DatabaseException;
import main.java.server.services.Create;
import main.java.server.services.Delete;
import main.java.server.services.Update;
import main.java.shared.request.UpsertRequest;
import main.java.shared.util.ISerializer;
import main.java.shared.util.Serializer;

import java.io.IOException;
import java.net.URI;



public class CrudHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ISerializer serializer = new Serializer();

        URI urlInfo = httpExchange.getRequestURI();

        String url = urlInfo.toString();

        String[] urlComponents = url.split("/");

        String contactName = "";
        if(urlComponents.length >= 3) {
            contactName = urlComponents[2];
        }



        try {
            if (httpExchange.getRequestMethod().toLowerCase().equals("get")) {
                // Send response
                serializer.sendResponse("Get!", httpExchange);

            } else if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
                // Unpackage request
                UpsertRequest request = (UpsertRequest) serializer.toObject(httpExchange.getRequestBody(), UpsertRequest.class);

                // Create entry
                Create creator = new Create();
                String response = creator.createContact(contactName, request.getContact());

                // Send Response
                serializer.sendResponse(response, httpExchange);

            } else if (httpExchange.getRequestMethod().toLowerCase().equals("put")) {
                // Unpackage Request
                UpsertRequest request = (UpsertRequest) serializer.toObject(httpExchange.getRequestBody(), UpsertRequest.class);

                // Update database
                Update updater = new Update();
                String response = updater.updateContact(contactName, request.getContact());

                // Send response
                serializer.sendResponse(response, httpExchange);
            } else if (httpExchange.getRequestMethod().toLowerCase().equals("delete")) {
                // Delete entry
                Delete deleter = new Delete();
                String response = deleter.deleteContact(contactName);

                // Send response
                serializer.sendResponse(response, httpExchange);
            }
        } catch (DatabaseException e){
            // Send response
            serializer.sendResponse(e.getMessage(),httpExchange);
        }
    }
}
