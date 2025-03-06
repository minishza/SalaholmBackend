package salah.api.salaholm.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.model.Prayer;
import salah.api.salaholm.repository.PrayerRepository;

@RestController
public class PrayerController {
    private final PrayerRepository prayerRepository;

    public PrayerController(PrayerRepository prayerRepository) {
        this.prayerRepository = prayerRepository;
    }

    @Cacheable("Test")
    @RequestMapping("/test")
    public ResponseEntity<Prayer> test() {
        Prayer prayer = Prayer.builder()
                .hijri("Hijri")
                .georgian("Georgian")
                .fajr("Fajr")
                .sunrise("Sunrise")
                .asr("Asr")
                .zuhr("Zuhr")
                .maghrib("Maghrib")
                .isha("Isha")
                .build();

        prayerRepository.save(prayer);
        return ResponseEntity.ok(prayerRepository.findAll().get(0));
    }
}
