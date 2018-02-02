package main.java.client;

import main.java.shared.model.Contact;
import main.java.shared.request.CreateRequest;

public class ServerAPI {
    private static ClientCommunicator facade;

    private static void init(String host, String port){
        facade = ClientCommunicator.server(host, port);
    }


    public static void createContact(String url) {
        try {
            String response = facade.post(url,new CreateRequest());
            System.out.println(response);
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.println("Failure!");
        }
    }

    public static Contact[] getContacts(String url){
        try {
            String response = facade.get(url);
            System.out.println(response);
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.println("Failure!");
        }
        return new Contact[1];
    }

    public static void updateContact(String url){

    }

    public static void deleteContact(String url){

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
        }
    }
}
