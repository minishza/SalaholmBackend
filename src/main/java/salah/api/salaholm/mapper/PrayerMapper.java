package salah.api.salaholm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.prayer.Prayer;
import salah.api.salaholm.entity.prayer.PrayerTime;
import salah.api.salaholm.util.IdGenerator;
import salah.api.salaholm.util.prayer.PrayerDateConverter;
import salah.api.salaholm.util.prayer.PrayerName;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PrayerMapper implements PrayerMapperInterface {
    private PrayerDateConverter prayerDateConverter;
    private IdGenerator idGenerator;

    @Override
    public Prayer toPrayer(String webElement, String city, String month) {
        String[] prayerData = parseWebElement(webElement);

        var prayer = Prayer.builder()
                .id(idGenerator.nextPrayerId())
                .build();

        var calendars = prayerDateConverter.createHijriAndGregorianPrayerCalendars(prayerData, month, prayer);
        //persist

        var dailyPrayers = buildPrayerTimes(prayerData, prayer);
        //persist

        return prayer;
    }

    private List<PrayerTime> buildPrayerTimes(String[] prayerData, Prayer prayer) {
        var prayerTimes = new ArrayList<PrayerTime>();
        for (int prayerOrder = 1; prayerOrder < prayerData.length; prayerOrder++) {
            prayerTimes.add(
                    buildPrayerTimeFromString(prayerData[prayerOrder])
                            .id(idGenerator.nextPrayerTimeId())
                            .prayerName(PrayerName.fromIndex(prayerOrder).orElseThrow(() -> new IllegalArgumentException("Invalid enum prayer index")))
                            .prayerId(prayer.getId())
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
