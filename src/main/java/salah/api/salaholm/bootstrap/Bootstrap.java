package salah.api.salaholm.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import salah.api.salaholm.repository.PrayerRepository;
import salah.api.salaholm.scraper.PrayerScraper;

@Component
public class Bootstrap implements CommandLineRunner {
    private final PrayerScraper prayerScraper;
    private final PrayerRepository prayerRepository;

    public Bootstrap(PrayerScraper prayerScraper, PrayerRepository prayerRepository) {
        this.prayerScraper = prayerScraper;
        this.prayerRepository = prayerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (prayerRepository.count() == 0) {
            prayerScraper.getPrayerTimesFromIslamiska();
        }
    }
}
