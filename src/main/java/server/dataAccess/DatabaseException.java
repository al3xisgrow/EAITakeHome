package server.dataAccess;



public class DatabaseException extends Exception {
    public static String NOT_FOUND = "Hmmm, that name isn't in your address book. Maybe you should add it.";
    public static String DUPLICATE = "Hey! You've already got that name in your address book. Try a new one.";


    public DatabaseException(String s) {
        super(s);
    }

    public DatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
