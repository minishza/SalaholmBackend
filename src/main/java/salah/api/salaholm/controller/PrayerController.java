package salah.api.salaholm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.model.Prayer;
import salah.api.salaholm.repository.PrayerRepository;

import java.util.List;

@RestController
@RequestMapping("/prayer")
@RequiredArgsConstructor
public class PrayerController {
    private final PrayerRepository prayerRepository;

    @GetMapping("/test")
    public List<Prayer> test() {
        return prayerRepository.findAll();
    }
}
