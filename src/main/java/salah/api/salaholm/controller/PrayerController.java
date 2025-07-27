package salah.api.salaholm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.repository.PrayerRepository;
import salah.api.salaholm.scraper.PrayerScraper;
import salah.api.salaholm.util.parser.LocationProvider;

@RestController
@RequestMapping("/prayer")
@RequiredArgsConstructor
public class PrayerController {
    private final PrayerRepository prayerRepository;
    private final PrayerScraper prayerScraper;
    private final LocationProvider locationProvider;


    @GetMapping("/test")
    public Location test(@RequestParam String city) {
        prayerScraper.getIslamiskaAnnualPrayers(city);
        return prayerRepository.findLocationByMunicipality(city);
    }
}