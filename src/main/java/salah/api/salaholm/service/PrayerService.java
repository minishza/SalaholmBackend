package salah.api.salaholm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salah.api.salaholm.repository.LocationPrayerRepository;
import salah.api.salaholm.util.scraper.PrayerScraper;

@Service
@RequiredArgsConstructor
public class PrayerService {
    private final LocationPrayerRepository locationPrayerRepository;
    private final PrayerScraper prayerScraper;
}
