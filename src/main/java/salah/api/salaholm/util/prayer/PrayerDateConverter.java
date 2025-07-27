package salah.api.salaholm.util.prayer;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.prayer.Prayer;
import salah.api.salaholm.util.CalendarType;
import salah.api.salaholm.util.IdGenerator;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PrayerDateConverter {
    private SimpleDateFormat formatter;
    private IdGenerator idGenerator;

    public List<PrayerCalendar> createHijriAndGregorianPrayerCalendars(String[] data, String monthName, Prayer prayer) {
        int date = Integer.parseInt(data[0]);
        var gregorianCalendar = prepareGregorianBuilder(date, monthName);
        var hijriCalendar = new UmmalquraCalendar();
        hijriCalendar.setTime(gregorianCalendar.getTime());

        Long prayerId = prayer.getId();

        return List.of(
                toPrayerCalendar(gregorianCalendar, prayerId, CalendarType.GREGORIAN),
                toPrayerCalendar(hijriCalendar, prayerId, CalendarType.HIJRI)
        );
    }


    private PrayerCalendar toPrayerCalendar(Calendar calendar, Long prayerId, CalendarType type) {
        formatter.setCalendar(calendar);
        CalendarData calendarData = toCalendarData(calendar);

        return buildPrayerCalendar(calendarData, prayerId)
                .calendarType(type)
                .build();
    }


    private CalendarData toCalendarData(Calendar calendar) {
        String formatted = formatter.format(calendar.getTime());
        String[] parts = formatted.split("\\s*,\\s*");

        if (parts.length != 4) {
            throw new IllegalStateException("Unexpected calendar format: " + formatted);
        }

        String day = parts[0];
        int date = Integer.parseInt(parts[1]);
        String month = parts[2];
        int year = Integer.parseInt(parts[3]);

        return new CalendarData(date, day, month, year, formatted);
    }

    private PrayerCalendar.PrayerCalendarBuilder buildPrayerCalendar(CalendarData calendarData, Long prayerId) {
        return PrayerCalendar.builder()
                .id(idGenerator.nextPrayerCalendarId())
                .date(calendarData.date)
                .dayOfWeek(calendarData.day)
                .month(calendarData.month)
                .year(calendarData.year)
                .formattedCalendar(calendarData.formatted)
                .prayerId(prayerId)
                .important(false);
    }

    private int toMonth(String monthName) {

        Map<String, Integer> swedishMonthMap = Map.ofEntries(
                Map.entry("januari", Calendar.JANUARY),
                Map.entry("februari", Calendar.FEBRUARY),
                Map.entry("mars", Calendar.MARCH),
                Map.entry("april", Calendar.APRIL),
                Map.entry("maj", Calendar.MAY),
                Map.entry("juni", Calendar.JUNE),
                Map.entry("juli", Calendar.JULY),
                Map.entry("augusti", Calendar.AUGUST),
                Map.entry("september", Calendar.SEPTEMBER),
                Map.entry("oktober", Calendar.OCTOBER),
                Map.entry("november", Calendar.NOVEMBER),
                Map.entry("december", Calendar.DECEMBER)
        );

        return swedishMonthMap.get(monthName.toLowerCase());
    }

    private GregorianCalendar prepareGregorianBuilder(int date, String monthName) {
        int month = toMonth(monthName);
        int year = Year.now().getValue();

        return new GregorianCalendar(year, month, date);
    }

    private record CalendarData(int date, String day, String month, int year, String formatted) {}
}
