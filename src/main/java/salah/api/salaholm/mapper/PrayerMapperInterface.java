package salah.api.salaholm.mapper;

import salah.api.salaholm.entity.prayer.Prayer;

public interface PrayerMapperInterface {
    Prayer toPrayer(String prayerData, String city, String month);
}