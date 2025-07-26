package salah.api.salaholm.entity.calendar;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import salah.api.salaholm.entity.prayer.Prayer;
import salah.api.salaholm.util.CalendarType;

@Entity
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PrayerCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int date;
    private String dayOfWeek;
    private String month;
    private int year;

    private CalendarType calendarType;
    private boolean important;

    @ManyToOne
    @JoinColumn(name = "prayer_id", nullable = false)
    private Prayer prayer;
}