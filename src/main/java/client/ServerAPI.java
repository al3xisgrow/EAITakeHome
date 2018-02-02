package main.java.client;

import main.java.shared.model.Contact;
import main.java.shared.request.CreateRequest;
import main.java.shared.request.UpdateRequest;

public class ServerAPI {
    private static ClientCommunicator facade;

    private static void init(String host, String port){
        facade = ClientCommunicator.server(host, port);
    }


    public static void createContact(String url) {
        try {
            String response = facade.post("POST", url, new CreateRequest());
            System.out.println(response);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Contact[] getContacts(String url){
        try {
            String response = facade.get(url);
            System.out.println(response);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
        return new Contact[1];
    }

    public static void updateContact(String url){
        try {
            String response = facade.post("PUT", url, new UpdateRequest());
            System.out.println(response);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteContact(String url){
        try {
            String response = facade.post("DELETE", url, null);
            System.out.println(response);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("ERROR. Please enter serverHost (IP) and serverPort as command line arguments (in that order).");
        } else {
            String serverHost = args[0];
            String serverPort = args[1];

            init(serverHost, serverPort);

            createContact("/contact");
            getContacts("/contact/testName");
            updateContact("/contact/testName");
            deleteContact("/contact/testName");
        }
    }
}
