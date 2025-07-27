package salah.api.salaholm.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.scraper.PrayerScraper;

@Service
public class WarmUpService {
    private final PrayerScraper prayerScraper;

    public WarmUpService(PrayerScraper prayerScraper) {
        this.prayerScraper = prayerScraper;
    }

    @Cacheable(value = "locationCache", key = "#city.toLowerCase()")
    public Location warmupPrayers(String city) {
        return prayerScraper.getAnnualPrayersByLocation(city);
    }
}
