package salah.api.salaholm.util.prayer;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.util.CalendarType;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class PrayerDateConverter {
    private SimpleDateFormat formatter;

    public List<PrayerCalendar> createHijriAndGregorianPrayerCalendars(List<String> data, int month, Prayers prayer) {
        int date = Integer.parseInt(data.get(0));
        var gregorianCalendar = new GregorianCalendar(Year.now().getValue(), month-1, date);
        var hijriCalendar = new UmmalquraCalendar();
        hijriCalendar.setTime(gregorianCalendar.getTime());

        var gregorianPrayerCalendar = toPrayerCalendarOfGregorian(gregorianCalendar, prayer);
        var hijriPrayerCalendar = toPrayerCalendarOfHijri(hijriCalendar, prayer);

        return List.of(gregorianPrayerCalendar, hijriPrayerCalendar);
    }

    private PrayerCalendar toPrayerCalendarOfHijri(UmmalquraCalendar hijriCalendar, Prayers prayers) {
        formatter.setCalendar(hijriCalendar);
        CalendarData formattedHijri = toCalendarData(hijriCalendar, prayers);

        return buildPrayerCalendar(formattedHijri)
                .calendarType(CalendarType.HIJRI)
                .build();
    }
    private PrayerCalendar toPrayerCalendarOfGregorian(GregorianCalendar gregorianCalendar, Prayers prayers) {
        formatter.setCalendar(gregorianCalendar);
        CalendarData formattedGregorian = toCalendarData(gregorianCalendar, prayers);

        return buildPrayerCalendar(formattedGregorian)
                .calendarType(CalendarType.GREGORIAN)
                .build();
    }

    private CalendarData toCalendarData(GregorianCalendar calendar, Prayers prayers) {
        String formattedCalendar = formatter.format(calendar.getTime());

        String[] formattedData = formattedCalendar.split("\\s*,\\s*");
        int date = Integer.parseInt(formattedData[1]);
        String day = formattedData[0];
        String month = formattedData[2];
        int year = Integer.parseInt(formattedData[3]);

        return new CalendarData(date, day, month, year, formattedCalendar, prayers);
    }

    private PrayerCalendar.PrayerCalendarBuilder buildPrayerCalendar(CalendarData calendarData) {
        return PrayerCalendar.builder()
                .date(calendarData.date)
                .dayOfWeek(calendarData.day)
                .month(calendarData.month)
                .year(calendarData.year)
                .formattedCalendar(calendarData.formatted)
                .prayers(calendarData.prayers)
                .important(false);
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

    private record CalendarData(int date, String day, String month, int year, String formatted, Prayers prayers) {}
}
