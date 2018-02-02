package main.java.shared.util;


import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;

public interface ISerializer {
    /**
     * Converts a String of JSON into an Object of the specified class
     * @param jsonObj InputStream containing the JSON data
     * @param c  class of the Object to retrieve from the String
     * @return Object, with the data from the String of JSON
     */
    Object toObject(InputStream jsonObj, Class c) throws IOException;

    /**
     * Reads an InputStream into a String
     * @param is InputStream to be read
     * @return convertedString
     * @throws IOException
     */
    String readStream(InputStream is) throws IOException;

    /**
     * Sends the response to an HTTP exchange with the given message
     * @param message Message to be included in the response
     * @param exchange HTTPExchange that is added to and sent
     */
    void sendResponse(String message, HttpExchange exchange);

}
