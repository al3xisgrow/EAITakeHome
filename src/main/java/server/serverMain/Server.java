package server.serverMain;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.dataAccess.DatabaseException;
import server.dataAccess.ElasticsearchFactory;
import server.dataAccess.IDatabaseFactory;
import server.handler.CrudHandler;
import server.handler.DefaultHandler;



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
        if(args.length < 4){
            System.out.println("ERROR: Too few arguments. Enter [server port number] (usually 8080) " +
                    "[Elasticsearch host IP] (127.0.0.1 for me) [Elasticsearch port1] (between 9300-9400) " +
                    "[Elasticsearch port2] (between 9300-9400).\n" +
                    "Example: \"8080 127.0.0.1 9300 9301\""
            );
        }
        else {
            String portNumber = args[0];
            try {
                String elasticsearchHost = args[1];
                String elasticsearchPort1 = args[2];
                String elasticsearchPort2 = args[3];
                IDatabaseFactory factory = new ElasticsearchFactory();
                factory.getDatabase().init(elasticsearchHost, elasticsearchPort1, elasticsearchPort2);
            } catch (DatabaseException e) {
                System.out.println(e.getMessage());
                return;
            }
            new Server().run(portNumber);
        }
    }
}
