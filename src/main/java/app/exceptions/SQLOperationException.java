package app.exceptions;

public class SQLOperationException extends RuntimeException {
    public SQLOperationException(String message) {
        super(message);
    }
}
