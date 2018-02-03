package client;

import com.google.gson.Gson;
import shared.util.Serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientCommunicator {
    private static String host;
    private static int port;
    private static String url;

    public static ClientCommunicator server(String host, String port){
        if(_instance == null){
            _instance = new ClientCommunicator(host, port);
        }
        return _instance;
    }

    private static ClientCommunicator _instance;

    private ClientCommunicator(String host, String port) {
        this.host = host;
        this.port = Integer.parseInt(port);
        this.url = "http://" + this.host + ":" + this.port;
    }


    public String post(String requestMethod, String urlPath, Object request) throws ClientException {
        try {
            URL fullURL = new URL(this.url + urlPath);
            HttpURLConnection connection = (HttpURLConnection) fullURL.openConnection();

            // Allow for input & output
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Set the request method
            connection.setRequestMethod(requestMethod);

            // Specify JSON
            connection.addRequestProperty("Accept", "application/json");

            // Connect!
            connection.connect();

            // Send request
            Gson g = new Gson();
            String json = g.toJson(request);

            OutputStreamWriter requestBody = new OutputStreamWriter(connection.getOutputStream());
            requestBody.write(json);
            requestBody.close();


            // Get response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream output = connection.getInputStream();

                // Read response
                Serializer serializer = new Serializer();
                String serverResponse = serializer.readStream(output);

                output.close();

                // Convert output from server into useful info
                // (is String OK? then convert that to Object from JSON?)
                return serverResponse;
            } else {
                throw new ClientException(ClientException.SERVER_ERROR + "HTTP code not OK!");
            }

        } catch (MalformedURLException e) {
            throw new ClientException(ClientException.SERVER_ERROR + e.getMessage());
        } catch (IOException e) {
            throw new ClientException(ClientException.SERVER_ERROR + e.getMessage());
        }
    }

    public String get(String urlPath) throws ClientException {
        try {

            // Open connection
            URL fullURL = new URL(this.url + urlPath);
            HttpURLConnection connection = (HttpURLConnection) fullURL.openConnection();

            // Enable output reception
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            // Specify JSON 
            connection.addRequestProperty("Accept", "application/json");

            // Connect!
            connection.connect();

            // Get response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream output = connection.getInputStream();

                // Read response
                Serializer serializer = new Serializer();
                String serverResponse = serializer.readStream(output);

                output.close();

                // Convert output from server into useful info
                // (is String OK? then convert that to Object from JSON?)
                return serverResponse;
            } else {
                throw new ClientException(ClientException.SERVER_ERROR + "HTTP code not OK!");
            }
        } catch (MalformedURLException e) {
            throw new ClientException(ClientException.SERVER_ERROR + e.getMessage());
        } catch (IOException e) {
            throw new ClientException(ClientException.SERVER_ERROR + e.getMessage());
        }
    }

}
