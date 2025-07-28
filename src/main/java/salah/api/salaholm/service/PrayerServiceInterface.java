package salah.api.salaholm.service;

import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayersDTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface PrayerServiceInterface {
    Optional<LocationDTO> getLocationDTO(String city);

    Optional<PrayersDTO> getPrayersByCity(String city);

    Optional<PrayersDTO> getPrayersByDate();

    List<PrayersDTO> getMonthlyPrayers(String city, YearMonth month);

    List<PrayersDTO> getAllAnnualPrayers(String city);

    List<String> getSupportedCities();

    boolean locationExists(String city);

    String getMunicipality(String city);

    double[] getCoordinates(String city);

    String getFormattedLocationName(String city);

    List<LocalDate> getAvailablePrayerDates(String city);
}