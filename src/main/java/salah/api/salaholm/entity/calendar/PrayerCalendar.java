package salah.api.salaholm.entity.calendar;

import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.prayer.PrayerTime;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.util.CalendarType;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
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

    @ManyToOne
    @JoinColumn(name = "prayers_id", nullable = false)
    private Prayers prayers;
}