package main.java.shared.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Serializer {

    private static Gson gson = new Gson();

    private Serializer() {}

    /**
     * Converts the given Object to a string of JSON
     * @param o Object to convert
     * @return A serialized representation of the object
     */
    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    /**
     * Converts a String of JSON into an Object of the specified class
     * @param jsonObj InputStream containing the JSON data
     * @param c  class of the Object to retrieve from the String
     * @return Object, with the data from the String of JSON
     */
    public static Object fromJson(InputStream jsonObj, Class c) throws IOException {
        String json = readStream(jsonObj);
        return gson.fromJson(json, c);
    }


    /**
     * Reads an InputStream into a String
     * @param is InputStream to be read
     * @return convertedString
     * @throws IOException
     */
    public static String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}