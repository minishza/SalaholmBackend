package salah.api.salaholm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.repository.PrayerRepository;

@RestController
@RequestMapping("/prayer")
@RequiredArgsConstructor
public class PrayerController {
    private final PrayerRepository prayerRepository;
}
