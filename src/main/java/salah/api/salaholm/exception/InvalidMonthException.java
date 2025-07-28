package salah.api.salaholm.exception;

public class InvalidMonthException extends RuntimeException  {
    public InvalidMonthException() {}
    public InvalidMonthException(String message) {
        super(message);
    }
    public InvalidMonthException(String message, Throwable cause) {
        super(message, cause);
    }
}
