package salah.api.salaholm.exception;

public class CityNotSupportedException extends RuntimeException  {
    public CityNotSupportedException() {}
    public CityNotSupportedException(String message) {super(message);}
    public CityNotSupportedException(String message, Throwable cause) {super(message, cause);}
}
