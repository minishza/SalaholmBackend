package salah.api.salaholm.entity.calendar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.util.CalendarType;

import java.io.Serial;
import java.io.Serializable;

@Entity
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class PrayerCalendar implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int date;
    private String dayOfWeek;
    private String month;
    private int year;
    private String formattedCalendar;

    private CalendarType calendarType;
    private boolean important;

    @ManyToOne
    @JoinColumn(name = "prayer_id", nullable = false)
    @JsonBackReference
    private Prayers prayer;
}