package salah.api.salaholm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.mapper.DTOMapper;
import salah.api.salaholm.repository.LocationPrayerRepository;
import salah.api.salaholm.scraper.PrayerScraper;
import salah.api.salaholm.util.parser.LocationProvider;

@RestController
@RequestMapping("/prayer")
@RequiredArgsConstructor
public class PrayerController {
    private final LocationPrayerRepository prayerRepository;
    private final PrayerScraper prayerScraper;
    private final LocationProvider locationProvider;
    private final DTOMapper dtoMapper;

    private final RedisTemplate<String, Object> locationRedisTemplate;


    @GetMapping("/test")
    public ResponseEntity<LocationDTO> test(@RequestParam String city) {
        var locationDTO = (LocationDTO) locationRedisTemplate.opsForValue().get(city);
        if (locationDTO == null) {
            Location location = prayerRepository.save(prayerScraper.getAnnualPrayersByLocation(city));
            return ResponseEntity.ok(dtoMapper.toLocationDTO(location));
        }

        return ResponseEntity.ok(locationDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<LocationDTO> getAllLocations(@RequestParam String... options) {
        return ResponseEntity.ok(dtoMapper.toLocationDTO(prayerRepository.findAll().get(0)));
    }
}