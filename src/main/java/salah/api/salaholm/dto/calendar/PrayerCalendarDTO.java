package salah.api.salaholm.dto.calendar;

import lombok.Data;
import salah.api.salaholm.entity.prayer.Prayer;
import salah.api.salaholm.util.CalendarType;

@Data
public class PrayerCalendarDTO {
    private int date;
    private String dayOfWeek;
    private String month;
    private int year;
    private String formattedCalendar;

    private CalendarType calendarType;
    private boolean important;

    private Prayer prayer;
}
