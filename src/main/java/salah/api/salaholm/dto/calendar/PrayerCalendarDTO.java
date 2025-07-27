package salah.api.salaholm.dto.calendar;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.dto.prayer.PrayerDTO;
import salah.api.salaholm.util.CalendarType;

@Data
@Builder
public class PrayerCalendarDTO {
    private int date;
    private String dayOfWeek;
    private String month;
    private int year;
    private String formattedCalendar;

    private CalendarType calendarType;
    private boolean important;

    private PrayerDTO prayer;
}
