package salah.api.salaholm.service;

import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayerTimeDTO;
import salah.api.salaholm.dto.prayer.PrayersDTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface PrayerServiceInterface {
    LocationDTO getLocationDTO(String city);
    PrayersDTO getPrayersByCity(String city);
    List<PrayerTimeDTO> getPrayersByDate();

    List<PrayersDTO> getMonthlyPrayers(String city, YearMonth month);

    List<PrayersDTO> getAllAnnualPrayers(String city);

    List<String> getSupportedCities();

    boolean locationExists(String city);

    String getMunicipality(String city);

    double[] getCoordinates(String city);

    String getFormattedLocationName(String city);

    List<LocalDate> getAvailablePrayerDates(String city);
}