package main.java.server.serverMain;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import main.java.server.handler.CrudHandler;
import main.java.server.handler.DefaultHandler;


/**
 * Created by Alexis on 2/1/18.
 */

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run(String portNumber) {
        // System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // Use the default executor

        // System.out.println("Creating contexts");
        server.createContext("/contact", new CrudHandler());
        server.createContext("/", new DefaultHandler());


        System.out.println("Starting server");
        server.start();
    }

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("ERROR: Please enter port number as first argument.");
        }
        else {
            String portNumber = args[0];
            new Server().run(portNumber);
        }
    }
}
