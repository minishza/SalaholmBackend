package salah.api.salaholm.mapper;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import salah.api.salaholm.model.Prayer;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

@Component
@AllArgsConstructor
public class PrayerMapper implements PrayerMapperInterface {
    private SimpleDateFormat formatter;

    @Override
    public Prayer toPrayer(WebElement webElement, String city, String month) {
        String[] prayerData = parseWebElement(webElement);

        return
                hijriAndGregorianCalendarPrayer(prayerData, month)
                .fajr(prayerData[1])
                .sunrise(prayerData[2])
                .zuhr(prayerData[3])
                .asr(prayerData[4])
                .maghrib(prayerData[5])
                .isha(prayerData[6])
                .city(city)
                .build();
    }


    private String[] parseWebElement(WebElement prayerElement) {
        return prayerElement.getText().split(" ");
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

    private Prayer.PrayerBuilder hijriAndGregorianCalendarPrayer(String[] data, String monthName) {
        int date = Integer.parseInt(data[0]);
        int month = toMonth(monthName);
        int year = Year.now().getValue();

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, date);
        Calendar hijriCalendar = new UmmalquraCalendar();
        hijriCalendar.setTime(gregorianCalendar.getTime());

        formatter.setCalendar(hijriCalendar);
        String formattedHijri = formatter.format(hijriCalendar.getTime());

        formatter.setCalendar(gregorianCalendar);
        String formattedGregorian = formatter.format(gregorianCalendar.getTime());

        return Prayer.builder()
                .hijri(formattedHijri)
                .gregorian(formattedGregorian);
    }
}
