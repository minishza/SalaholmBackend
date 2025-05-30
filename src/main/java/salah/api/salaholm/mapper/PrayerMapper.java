package salah.api.salaholm.mapper;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.model.Gregorian;
import salah.api.salaholm.model.Prayer;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Component
@AllArgsConstructor
public class PrayerMapper implements PrayerMapperInterface {
    private SimpleDateFormat formatter;

    @Override
    public Prayer toPrayer(String webElement, String city, String month) {
        String[] prayerData = parseWebElement(webElement);

        Map<String, ?> prayerCalendars = hijriAndGregorianCalendarPrayer(prayerData, month);

        Prayer prayer = Prayer.builder()
                .fajr(prayerData[1])
                .sunrise(prayerData[2])
                .zuhr(prayerData[3])
                .asr(prayerData[4])
                .maghrib(prayerData[5])
                .isha(prayerData[6])
                .city(city)
                .hijri((String) prayerCalendars.get("hijri"))
                .build();

        Gregorian gregorian = toGregorianCalendarPrayer((GregorianCalendar) prayerCalendars.get("gregorian"), prayer);

        prayer.setGregorian(gregorian);

        return prayer;
    }

    private String[] parseWebElement(String prayerElement) {
        return prayerElement.split(" ");
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
    private Gregorian toGregorianCalendarPrayer(GregorianCalendar calendar, Prayer prayer) {
        formatter.setCalendar(calendar);
        String[] formattedGregorian = formatter.format(calendar.getTime()).split(" ");
        return new Gregorian(
                Integer.parseInt(formattedGregorian[1]),
                formattedGregorian[0],
                formattedGregorian[2],
                Integer.parseInt(formattedGregorian[3]),
                prayer
        );
    }

    private Map<String, ?> hijriAndGregorianCalendarPrayer(String[] data, String monthName) {
        int date = Integer.parseInt(data[0]);
        int month = toMonth(monthName);
        int year = Year.now().getValue();

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, date);
        Calendar hijriCalendar = new UmmalquraCalendar();
        hijriCalendar.setTime(gregorianCalendar.getTime());

        formatter.setCalendar(hijriCalendar);
        String formattedHijri = formatter.format(hijriCalendar.getTime());

        return Map.of(
          "gregorian", gregorianCalendar,
                "hijri", formattedHijri
        );
    }
}
