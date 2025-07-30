package salah.api.salaholm.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.entity.prayer.PrayerTime;
import salah.api.salaholm.util.prayer.PrayerDateConverter;
import salah.api.salaholm.util.prayer.PrayerName;

import java.util.*;

@Component
@AllArgsConstructor
public class PrayerMapper {
    private PrayerDateConverter prayerDateConverter;

    public Prayers toPrayers(String webElement, String city, String month) {
        String[] prayerData = parseWebElement(webElement);

        Prayers prayer = Prayers.builder().build();

        List<PrayerCalendar> calendars = prayerDateConverter.createHijriAndGregorianPrayerCalendars(prayerData, month, prayer);

        List<PrayerTime> dailyPrayers = buildPrayerTimes(prayerData, calendars);
        calendars.forEach(calendar -> {
            calendar.setPrayerTimes(dailyPrayers);
        });

        prayer.setPrayerCalendars(calendars);

        return prayer;
    }

    private List<PrayerTime> buildPrayerTimes(String[] prayerData, List<PrayerCalendar> prayerCalendar) {
        List<PrayerTime> prayerTimes = new ArrayList<>();
        for (int prayerOrder = 1; prayerOrder < prayerData.length; prayerOrder++) {
            prayerTimes.add(
                    buildPrayerTimeFromString(prayerData[prayerOrder])
                            .prayerName(PrayerName.fromIndex(prayerOrder).orElseThrow(() -> new IllegalArgumentException("Invalid enum prayer index")))
                            .prayerCalendar(prayerCalendar)
                            .build()
            );
        }
        return prayerTimes;
    }

    private PrayerTime.PrayerTimeBuilder buildPrayerTimeFromString(String prayerData) {
        String[] prayerHourAndMinute = prayerData.split(":");
        return PrayerTime.builder()
                .hour(prayerHourAndMinute[0])
                .minute(prayerHourAndMinute[1]);
    }

    private String[] parseWebElement(String prayerElement) {
        return prayerElement.split(" ");
    }
}
