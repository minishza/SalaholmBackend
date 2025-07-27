package salah.api.salaholm.entity.calendar;

import lombok.Builder;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import salah.api.salaholm.util.CalendarType;

@Builder
public class PrayerCalendar {

    @QuerySqlField(index = true)
    private Long id;

    private int date;
    private String dayOfWeek;
    @QuerySqlField(index = true)
    private String month;
    private int year;
    @QuerySqlField(index = true)
    private String formattedCalendar;

    private CalendarType calendarType;
    @QuerySqlField(index = true)
    private boolean important;

    @QuerySqlField(index = true)
    private Long prayerId;
}