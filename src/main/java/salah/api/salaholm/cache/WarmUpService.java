package salah.api.salaholm.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.mapper.DTOMapper;
import salah.api.salaholm.repository.LocationPrayerRepository;
import salah.api.salaholm.util.scraper.PrayerScraper;

@Service
@RequiredArgsConstructor
public class WarmUpService {
    private final PrayerScraper prayerScraper;
    private final DTOMapper  dtoMapper;
    private final LocationPrayerRepository prayerRepository;

    @Cacheable(key = "#city.toLowerCase()", value = "locationCache")
    public LocationDTO warmupPrayers(String city) {
        Location location = prayerRepository.save(prayerScraper.getAnnualPrayersByLocation(city));
        return dtoMapper.toLocationDTO(location);
    }
}
