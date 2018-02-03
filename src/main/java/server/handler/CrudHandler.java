package server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.dataAccess.DatabaseException;
import server.services.Create;
import server.services.Delete;
import server.services.Read;
import server.services.Update;
import shared.model.Contact;
import shared.request.UpsertRequest;
import shared.response.ReadResponse;
import shared.response.StandardResponse;
import shared.util.ISerializer;
import shared.util.Serializer;

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
                // Standard get (one contact, by name)
                if(!contactName.equals("")){
                    Read reader = new Read();
                    Contact contact = reader.readContact(contactName);

                    ReadResponse response = new ReadResponse();
                    response.addContact(contact);
                    response.setMessage("Success!");

                    serializer.sendResponse(serializer.toJson(response),httpExchange);
                } else {
                    // Fancy get. I didn't have time to do this one.
                    ReadResponse response = new ReadResponse();
                    response.setMessage("Didn't have time to implement this one :(");
                    serializer.sendResponse(serializer.toJson(response), httpExchange);
                }


            } else if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
                // Unpackage request
                UpsertRequest request = (UpsertRequest) serializer.toObject(httpExchange.getRequestBody(), UpsertRequest.class);

                // Create entry
                Create creator = new Create();
                StandardResponse response = new StandardResponse(creator.createContact(request.getContact()));

                // Send Response
                serializer.sendResponse(serializer.toJson(response), httpExchange);

            } else if (httpExchange.getRequestMethod().toLowerCase().equals("put")) {
                // Unpackage Request
                UpsertRequest request = (UpsertRequest) serializer.toObject(httpExchange.getRequestBody(), UpsertRequest.class);

                // Update database
                Update updater = new Update();
                StandardResponse response = new StandardResponse(updater.updateContact(contactName, request.getContact()));

                // Send response
                serializer.sendResponse(serializer.toJson(response), httpExchange);
            } else if (httpExchange.getRequestMethod().toLowerCase().equals("delete")) {
                // Delete entry
                Delete deleter = new Delete();
                StandardResponse response = new StandardResponse(deleter.deleteContact(contactName));

                // Send response
                serializer.sendResponse(serializer.toJson(response), httpExchange);
            }
        } catch (DatabaseException e){
            // Send response
            serializer.sendResponse(e.getMessage(), httpExchange);
        }
    }
}
