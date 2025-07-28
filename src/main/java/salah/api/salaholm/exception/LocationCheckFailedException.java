package salah.api.salaholm.exception;

public class LocationCheckFailedException extends RuntimeException  {
    public LocationCheckFailedException() {}
    public LocationCheckFailedException(String message) {
        super(message);
    }
    public LocationCheckFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
