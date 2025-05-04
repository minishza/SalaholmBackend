package salah.api.salaholm.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import salah.api.salaholm.scraper.PrayerScraper;

@Component
public class Bootstrap implements CommandLineRunner {
    private final PrayerScraper prayerScraper;

    public Bootstrap(PrayerScraper prayerScraper) {
        this.prayerScraper = prayerScraper;
    }

    @Override
    public void run(String... args) throws Exception {
        prayerScraper.getPrayerTimesFromIslamiska();
    }
}
