package salah.api.salaholm.exception;

public class PrayerDateNotProvidedException extends RuntimeException  {
    public PrayerDateNotProvidedException() {}
    public PrayerDateNotProvidedException(String message) {
        super(message);
    }
    public PrayerDateNotProvidedException(String message, Throwable cause) {
        super(message, cause);
    }
}
