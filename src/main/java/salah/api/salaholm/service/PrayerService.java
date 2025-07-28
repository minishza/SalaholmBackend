package salah.api.salaholm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayerTimeDTO;
import salah.api.salaholm.dto.prayer.PrayersDTO;
import salah.api.salaholm.exception.LocationNotFoundException;
import salah.api.salaholm.mapper.DTOMapper;
import salah.api.salaholm.repository.LocationPrayerRepository;
import salah.api.salaholm.util.scraper.PrayerScraper;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrayerService implements PrayerServiceInterface {
    private final LocationPrayerRepository locationPrayerRepository;
    private final PrayerScraper prayerScraper;
    private final DTOMapper mapper;
    private final LocationCacheService locationCacheService;

    @Override
    public LocationDTO getLocationDTO(String city) {
        LocationDTO location = locationCacheService.getOrCacheLocationByCity(city);
        if (location == null) {
            throw new LocationNotFoundException(
                    String.format("Location was not found with %s inside getLocationDTO", city)
            );
        }
        return location;
    }

    @Override
    public PrayersDTO getPrayersByCity(String city) {
        return null;
    }

    @Override
    public List<PrayerTimeDTO> getPrayersByDate() {
        return List.of();
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
}
