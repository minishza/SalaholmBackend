package salah.api.salaholm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.mapper.DTOMapper;
import salah.api.salaholm.repository.PrayerRepository;
import salah.api.salaholm.util.scraper.PrayerScraper;

@Service
@RequiredArgsConstructor
public class LocationCacheService {
    private final PrayerRepository locationPrayerRepository;
    private final PrayerScraper prayerScraper;
    private final DTOMapper mapper;

    @Cacheable(key = "#city.toLowerCase()", value = "locationCache")
    public LocationDTO getOrCacheLocationByCity(String city) {
        Location location = locationPrayerRepository.findLocationByCity(city)
                .orElseGet(() -> locationPrayerRepository.save(prayerScraper.getAnnualPrayersByLocation(city)));

        return mapper.toLocationDTO(location);
    }
}
