package main.java.server.dataAccess;

/**
 * Created by Alexis on 2/1/18.
 */

public class DatabaseException extends Exception {

    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
