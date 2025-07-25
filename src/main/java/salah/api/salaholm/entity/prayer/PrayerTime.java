package salah.api.salaholm.entity.prayer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalTime;

@Entity
@Table
public class PrayerTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prayerName;
    private int hour;
    private int minute;

    @ManyToOne
    @JoinColumn(name = "prayer_id")
    @JsonBackReference
    private Prayer prayer;

    public boolean isBefore(LocalTime time) {
        return toLocalTime().isBefore(time);
    }

    public boolean isAfter(LocalTime time) {
        return toLocalTime().isAfter(time);
    }

    public Duration timeUntil(LocalTime now) {
        return Duration.between(now, toLocalTime());
    }

    private LocalTime toLocalTime() {
        return LocalTime.of(hour, minute);
    }
}
