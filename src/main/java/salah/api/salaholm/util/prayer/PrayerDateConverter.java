package salah.api.salaholm.util.prayer;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.util.CalendarType;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Component
@AllArgsConstructor
public class PrayerDateConverter {
    private SimpleDateFormat formatter;

    public List<PrayerCalendar> createHijriAndGregorianPrayerCalendars(String[] data, String monthName, Prayers prayer) {
        int date = Integer.parseInt(data[0]);
        var gregorianCalendar = prepareGregorianBuilder(date, monthName);
        var hijriCalendar = new UmmalquraCalendar();
        hijriCalendar.setTime(gregorianCalendar.getTime());

        var gregorianPrayerCalendar = toPrayerCalendarOfGregorian(gregorianCalendar, prayer);
        var hijriPrayerCalendar = toPrayerCalendarOfHijri(hijriCalendar, prayer);

        return List.of(gregorianPrayerCalendar, hijriPrayerCalendar);
    }

    private PrayerCalendar toPrayerCalendarOfHijri(UmmalquraCalendar hijriCalendar, Prayers prayer) {
        formatter.setCalendar(hijriCalendar);
        CalendarData formattedHijri = toCalendarData(hijriCalendar);

        return buildPrayerCalendar(formattedHijri)
                .calendarType(CalendarType.HIJRI)
                .prayer(prayer)
                .build();
    }


    private PrayerCalendar toPrayerCalendarOfGregorian(GregorianCalendar gregorianCalendar, Prayers prayer) {
        formatter.setCalendar(gregorianCalendar);
        CalendarData formattedGregorian = toCalendarData(gregorianCalendar);

        return buildPrayerCalendar(formattedGregorian)
                .calendarType(CalendarType.GREGORIAN)
                .prayer(prayer)
                .build();
    }

    private CalendarData toCalendarData(GregorianCalendar calendar) {
        String formattedCalendar = formatter.format(calendar.getTime());

        String[] formattedData = formattedCalendar.split("\\s*,\\s*");
        int date = Integer.parseInt(formattedData[1]);
        String day = formattedData[0];
        String month = formattedData[2];
        int year = Integer.parseInt(formattedData[3]);

        return new CalendarData(date, day, month, year, formattedCalendar);
    }

    private PrayerCalendar.PrayerCalendarBuilder buildPrayerCalendar(CalendarData calendarData) {
        return PrayerCalendar.builder()
                .date(calendarData.date)
                .dayOfWeek(calendarData.day)
                .month(calendarData.month)
                .year(calendarData.year)
                .formattedCalendar(calendarData.formatted)
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


    public int toMonthValue(String monthName) {
        Map<String, Integer> englishMap = Map.ofEntries(
                Map.entry("january", Calendar.JANUARY),
                Map.entry("february", Calendar.FEBRUARY),
                Map.entry("mars", Calendar.MARCH),
                Map.entry("april", Calendar.APRIL),
                Map.entry("may", Calendar.MAY),
                Map.entry("june", Calendar.JUNE),
                Map.entry("july", Calendar.JULY),
                Map.entry("august", Calendar.AUGUST),
                Map.entry("september", Calendar.SEPTEMBER),
                Map.entry("october", Calendar.OCTOBER),
                Map.entry("november", Calendar.NOVEMBER),
                Map.entry("december", Calendar.DECEMBER)
        );

        return englishMap.get(monthName.toLowerCase());
    }

    private GregorianCalendar prepareGregorianBuilder(int date, String monthName) {
        int month = toMonth(monthName);
        int year = Year.now().getValue();

        return new GregorianCalendar(year, month, date);
    }

    private record CalendarData(int date, String day, String month, int year, String formatted) {}
}
