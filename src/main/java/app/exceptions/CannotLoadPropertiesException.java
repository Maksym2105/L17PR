package app.exceptions;

public class CannotLoadPropertiesException extends RuntimeException {
    public CannotLoadPropertiesException(String message) {
        super(message);
    }
}
