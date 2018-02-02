package main.java.client;

public class ClientException extends Throwable {
    public ClientException(String s) {
        super(s);
    }

    public ClientException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
