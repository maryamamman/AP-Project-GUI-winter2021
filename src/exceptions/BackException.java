package exceptions;

public class BackException extends RuntimeException {
    String message;
    public BackException() {
        message = "Invalid command";
    }
    @Override
    public String getMessage() {
        return message;
    }
}