package salah.api.salaholm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.entity.Prayer;
import salah.api.salaholm.repository.PrayerRepository;
import salah.api.salaholm.scraper.PrayerScraper;

import java.util.List;

@RestController
@RequestMapping("/prayer")
@RequiredArgsConstructor
public class PrayerController {
    private final PrayerRepository prayerRepository;
    private final PrayerScraper prayerScraper;

    @GetMapping("/city")
    public List<Prayer> prayer(@RequestParam String city) {
        if (prayerRepository.findAllByCity(city).size() < 12) {
            prayerScraper.getMonthlyCityPrayerFromIslamiska(city);
        }
        return prayerRepository.findAllByCity(city);
    }

    @GetMapping("/test")
    public List<Prayer> test() {
        return prayerRepository.findAll();
    }
}