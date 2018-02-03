package client;

public class ClientException extends Throwable {

    public static final String INVALID_REQUEST_METHOD = "ERROR: Invalid Request Method. Please use GET, POST, PUT, or DELETE.";
    public static final String INVALID_PATH = "ERROR: Invalid Path. Please use one of the endpoints from the spec (i.e. the README)";
    public static final String SERVER_ERROR = "SERVER ERROR: ";

    public ClientException(String s) {
        super(s);
    }

    public ClientException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
