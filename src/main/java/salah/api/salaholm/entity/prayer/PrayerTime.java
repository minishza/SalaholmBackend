package salah.api.salaholm.entity.prayer;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import salah.api.salaholm.util.prayer.PrayerName;

import java.time.Duration;
import java.time.LocalTime;

@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class PrayerTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PrayerName prayerName;
    private String hour;
    private String minute;

    @ManyToOne
    @JoinColumn(name = "prayer_id")
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
        return LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
    }
}
