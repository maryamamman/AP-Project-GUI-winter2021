package exceptions;

//import controller.mission.Log;

public class IllegalCommandException extends RuntimeException {
    String message;
    public IllegalCommandException(String s) {
//        Log.logger.info("GameErrorException with message \"" + s + "\" was thrown.");
        message = s;
    }
    public IllegalCommandException() {
//        Log.logger.info("Invalid command was entered; GameErrorException was thrown.");
        message = "Invalid command";
    }
    @Override
    public String getMessage() {
        return message;
    }
}