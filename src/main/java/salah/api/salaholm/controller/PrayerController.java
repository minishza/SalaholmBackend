package salah.api.salaholm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.mapper.DTOMapper;
import salah.api.salaholm.repository.LocationPrayerRepository;
import salah.api.salaholm.util.parser.LocationProvider;
import salah.api.salaholm.util.scraper.PrayerScraper;

@RestController
@RequestMapping("/prayer/{city}")
@RequiredArgsConstructor
public class PrayerController {
    private final LocationPrayerRepository prayerRepository;
    private final PrayerScraper prayerScraper;
    private final LocationProvider locationProvider;
    private final DTOMapper dtoMapper;

    @GetMapping("/test")
    public ResponseEntity<LocationDTO> test(@PathVariable String city) {
        if (prayerRepository.findAll().size() > 1) {
            Location location = prayerRepository.findLocationByCity(city);
            LocationDTO locationDTO = dtoMapper.toLocationDTO(location);
            return ResponseEntity.ok(locationDTO);
        }

        Location location = prayerScraper.getAnnualPrayersByLocation(city);
        LocationDTO locationDTO = dtoMapper.toLocationDTO(location);
        return new ResponseEntity<>(locationDTO, HttpStatus.OK);
    }
}