package salah.api.salaholm.util.prayer;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.prayer.Prayer;
import salah.api.salaholm.util.CalendarType;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class PrayerDateConverter {
    private SimpleDateFormat formatter;

    public Set<PrayerCalendar> createHijriAndGregorianPrayerCalendars(String[] data, String monthName, Prayer prayer) {
        int date = Integer.parseInt(data[0]);
        var gregorianCalendar = buildGregorianCalendar(date, monthName);
        var hijriCalendar = new UmmalquraCalendar();
        hijriCalendar.setTime(gregorianCalendar.getTime());

        var gregorianPrayerCalendar = toPrayerCalendarOfGregorian(gregorianCalendar, prayer);
        var hijriPrayerCalendar = toPrayerCalendarOfHijri(hijriCalendar, prayer);

        return Set.of(gregorianPrayerCalendar, hijriPrayerCalendar);
    }

    private PrayerCalendar toPrayerCalendarOfHijri(UmmalquraCalendar hijriCalendar, Prayer prayer) {
        formatter.setCalendar(hijriCalendar);
        CalendarData formattedHijri = toCalendarData(hijriCalendar);

        return buildPrayerCalendar(formattedHijri)
                .calendarType(CalendarType.HIJRI)
                .prayer(prayer)
                .build();
    }


    private PrayerCalendar toPrayerCalendarOfGregorian(GregorianCalendar gregorianCalendar, Prayer prayer) {
        formatter.setCalendar(gregorianCalendar);
        CalendarData formattedGregorian = toCalendarData(gregorianCalendar);

        return buildPrayerCalendar(formattedGregorian)
                .calendarType(CalendarType.GREGORIAN)
                .prayer(prayer)
                .build();
    }

    private CalendarData toCalendarData(GregorianCalendar calendar) {
        String[] formattedData = formatter.format(calendar.getTime()).split(" ");
        int date = Integer.parseInt(formattedData[1]);
        String day = formattedData[0];
        String month = formattedData[2];
        int year = Integer.parseInt(formattedData[3]);

        return new CalendarData(date, day, month, year);
    }

    private PrayerCalendar.PrayerCalendarBuilder buildPrayerCalendar(CalendarData calendarData) {
        return PrayerCalendar.builder()
                .date(calendarData.date)
                .dayOfWeek(calendarData.day)
                .month(calendarData.month)
                .year(calendarData.year)
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

    private GregorianCalendar buildGregorianCalendar(int date, String monthName) {
        int month = toMonth(monthName);
        int year = Year.now().getValue();

        return new GregorianCalendar(year, month, date);
    }

    private record CalendarData(int date, String day, String month, int year) {}
}
