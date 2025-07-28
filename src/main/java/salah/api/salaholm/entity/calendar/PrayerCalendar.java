package salah.api.salaholm.entity.calendar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.entity.prayer.PrayerTime;
import salah.api.salaholm.util.CalendarType;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class PrayerCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "calendar_id")
    private Long id;

    private int date;
    private String dayOfWeek;
    private String month;
    private int year;
    private String formattedCalendar;

    private CalendarType calendarType;
    private boolean important;

    @ManyToMany
    @JoinTable(
            name = "prayer_calander_link",
            joinColumns = @JoinColumn(name = "calendar_id"),
            inverseJoinColumns = @JoinColumn(name = "prayer_time_id")
    )
    @JsonManagedReference
    private List<PrayerTime> prayerTimes;

    @ManyToOne
    @JoinColumn(name = "prayer_id", nullable = false)
    @JsonBackReference
    private Prayers prayer;
}