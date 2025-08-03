package salah.api.salaholm.entity.prayer;


import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.util.prayer.PrayerName;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class PrayerTime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "prayer_time_id")
    private Long id;

    private PrayerName prayerName;
    private String hour;
    private String minute;

    @ManyToOne
    @JoinColumn(name="prayers_id", nullable=false)
    private Prayers prayers;
}
