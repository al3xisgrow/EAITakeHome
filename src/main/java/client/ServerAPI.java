package client;

import shared.model.Contact;
import shared.request.UpsertRequest;
import shared.response.ReadResponse;
import shared.response.StandardResponse;
import shared.util.Serializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ServerAPI {
    private ClientCommunicator facade;
    private Serializer serializer;


    public void init(String host, String port){
        facade = ClientCommunicator.server(host, port);
        serializer = new Serializer();
    }


    public String createContact(String url, Contact contact) {
        try {
            UpsertRequest request = new UpsertRequest(contact);
            String json = facade.post("POST", url, request);
            try {
                StandardResponse response = (StandardResponse) serializer.toObject(json, StandardResponse.class);
                return response.getMessage();
            } catch (IOException e) {
                throw new ClientException("I/O Serialization problem in Client-side API!");
            }
        } catch (ClientException e) {
            return e.getMessage();
        }
    }

    public Set<Contact> getContacts(String url){
        try {
            String json = facade.get(url);
            ReadResponse response = (ReadResponse) serializer.toObject(json, ReadResponse.class);
            if(response.getMessage().equals("Success")){
                return response.getContacts();
            } else {
                throw new ClientException(ClientException.SERVER_ERROR + response.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
        return new HashSet<Contact>();
    }

    public String updateContact(String url, Contact contact){
        try {
            String json = facade.post("PUT", url, new UpsertRequest(contact));
            try {
                StandardResponse response = (StandardResponse) serializer.toObject(json, StandardResponse.class);
                return response.getMessage();
            } catch (IOException e) {
                throw new ClientException("I/O Serialization problem in Client-side API!");
            }
        } catch (ClientException e) {
            return e.getMessage();
        }
    }

    public String deleteContact(String url){
        try {
            String json = facade.post("DELETE", url, null);
            try {
                StandardResponse response = (StandardResponse) serializer.toObject(json, StandardResponse.class);
                return response.getMessage();
            } catch (IOException e) {
                throw new ClientException("I/O Serialization problem in Client-side API!");
            }
        } catch (ClientException e) {
            return e.getMessage();
        }
    }
    
}
