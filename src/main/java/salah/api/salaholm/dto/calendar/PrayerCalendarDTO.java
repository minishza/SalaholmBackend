package salah.api.salaholm.dto.calendar;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.util.CalendarType;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class PrayerCalendarDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int date;
    private String dayOfWeek;
    private String month;
    private int year;
    private String formattedCalendar;

    private CalendarType calendarType;
    private boolean important;
}
