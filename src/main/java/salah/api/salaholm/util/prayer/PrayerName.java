package salah.api.salaholm.util.prayer;

import java.util.Optional;

public enum PrayerName {
    FAJR,
    SUNRISE,
    ZUHR,
    ASR,
    MAGHRIB,
    ISHA;

    public int getIndex() {
        return switch (this) {
            case FAJR -> 1;
            case SUNRISE -> 2;
            case ZUHR -> 3;
            case ASR -> 4;
            case MAGHRIB -> 5;
            case ISHA -> 6;
        };
    }

    public static Optional<PrayerName> fromIndex(int index) {
        return switch (index) {
            case 1 -> Optional.of(FAJR);
            case 2 -> Optional.of(SUNRISE);
            case 3 -> Optional.of(ZUHR);
            case 4 -> Optional.of(ASR);
            case 5 -> Optional.of(MAGHRIB);
            case 6 -> Optional.of(ISHA);
            default -> Optional.empty();
        };
    }
}
