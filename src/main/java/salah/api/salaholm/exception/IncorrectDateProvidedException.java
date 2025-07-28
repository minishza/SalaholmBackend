package salah.api.salaholm.exception;

public class IncorrectDateProvidedException extends RuntimeException  {
    public IncorrectDateProvidedException() {}
    public IncorrectDateProvidedException(String message) {
        super(message);
    }
    public IncorrectDateProvidedException(String message, Throwable cause) {
        super(message, cause);
    }
}
