package salah.api.salaholm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.service.LocationCacheService;

@RestController
@RequestMapping("/prayer/{city}")
@RequiredArgsConstructor
public class PrayerController {
    private final LocationCacheService locationCacheService;

    @GetMapping("/test")
    public ResponseEntity<LocationDTO> test(@PathVariable String city) {
        return new ResponseEntity<>(locationCacheService.getOrCacheLocationByCity(city), HttpStatus.OK);
    }
}