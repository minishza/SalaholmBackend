package salah.api.salaholm.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.prayer.Prayer;
import salah.api.salaholm.util.PrayerDateConverter;

import java.util.*;

@Component
@AllArgsConstructor
public class PrayerMapper implements PrayerMapperInterface {
    private PrayerDateConverter prayerDateConverter;

    @Override
    public Prayer toPrayer(String webElement, String city, String month) {
        String[] prayerData = parseWebElement(webElement);

        Map<String, ?> prayerCalendars = prayerDateConverter.createHijriAndGregorianPrayerCalendars(prayerData, month);

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

        Gregorian gregorian = prayerDateConverter.toGregorianCalendarPrayer((GregorianCalendar) prayerCalendars.get("gregorian"), prayer);

        prayer.setGregorian(gregorian);

        return prayer;
    }

    private String[] parseWebElement(String prayerElement) {
        return prayerElement.split(" ");
    }


}
