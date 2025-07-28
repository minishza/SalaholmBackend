package salah.api.salaholm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CityNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleCityNotSupportedException(CityNotSupportedException ex, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LocationCheckFailedException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorResponse> handleLocationCheckFailedException(LocationCheckFailedException ex, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                ex
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleLocationNotFoundException(LocationNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrayerDateNotProvidedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlePrayerDateNotProvidedException(PrayerDateNotProvidedException ex, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse buildErrorResponse(int status, RuntimeException ex) {
        return new ErrorResponse(
                status,
                ex.getMessage(),
                LocalDateTime.now()
        );
    }
}