package salah.api.salaholm.entity.prayer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.util.prayer.PrayerName;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PrayerTime implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private PrayerName prayerName;
    private String hour;
    private String minute;

    @ManyToOne
    @JoinColumn(name = "prayer_id", nullable = false)
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
        return LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
    }
}
