package salah.api.salaholm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayersDTO;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.mapper.DTOMapper;
import salah.api.salaholm.repository.LocationPrayerRepository;
import salah.api.salaholm.util.scraper.PrayerScraper;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrayerService implements PrayerServiceInterface {
    private final LocationPrayerRepository locationPrayerRepository;
    private final PrayerScraper prayerScraper;
    private final DTOMapper mapper;

    @Override
    public Optional<LocationDTO> getLocationDTO(String city) {
        return Optional.empty();
    }

    @Override
    public Optional<PrayersDTO> getPrayersByCity(String city) {
        return Optional.empty();
    }

    @Override
    public Optional<PrayersDTO> getPrayersByDate() {
        return Optional.empty();
    }

    @Override
    public List<PrayersDTO> getMonthlyPrayers(String city, YearMonth month) {
        return List.of();
    }

    @Override
    public List<PrayersDTO> getAllAnnualPrayers(String city) {
        return List.of();
    }

    @Override
    public List<String> getSupportedCities() {
        return List.of();
    }

    @Override
    public boolean locationExists(String city) {
        return false;
    }

    @Override
    public String getMunicipality(String city) {
        return "";
    }

    @Override
    public double[] getCoordinates(String city) {
        return new double[0];
    }

    @Override
    public String getFormattedLocationName(String city) {
        return "";
    }

    @Override
    public List<LocalDate> getAvailablePrayerDates(String city) {
        return List.of();
    }

    @Cacheable(key = "#city.toLowerCase()", value = "locationCache")
    public LocationDTO getOrCacheLocationByCity(String city) {
        Location location = locationPrayerRepository.save(prayerScraper.getAnnualPrayersByLocation(city));
        return mapper.toLocationDTO(location);
    }
}
