package shared.model;

public class DataValidationException extends Exception {
    public static String NOT_REALISTIC = "You entered far too much data for that field. Try something with a character count less than ";


    public DataValidationException(String s) {
        super(s);
    }

    public DataValidationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
