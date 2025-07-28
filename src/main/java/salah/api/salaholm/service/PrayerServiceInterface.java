package salah.api.salaholm.service;

import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayersDTO;
import salah.api.salaholm.util.CalendarType;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface PrayerServiceInterface {
    LocationDTO getLocationDTO(String city);
    List<PrayersDTO> getPrayersByCity(String city);
    List<PrayerCalendarDTO> getPrayersByDate(String city, LocalDate date, CalendarType calendarType);

    List<PrayersDTO> getMonthlyPrayers(String city, YearMonth month);

    List<PrayersDTO> getAllAnnualPrayers(String city);

    List<String> getSupportedCities();

    boolean locationExists(String city);

    String getMunicipality(String city);

    double[] getCoordinates(String city);

    String getFormattedLocationName(String city);

    List<LocalDate> getAvailablePrayerDates(String city);
}