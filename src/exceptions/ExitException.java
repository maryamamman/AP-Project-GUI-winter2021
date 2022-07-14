package exceptions;

public class ExitException extends RuntimeException {
    String message;
    public ExitException() {
        message = "Invalid command";
    }
    @Override
    public String getMessage() {
        return message;
    }
}