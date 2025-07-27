package salah.api.salaholm.mapper;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;
import salah.api.salaholm.dto.location.CoordinatesDTO;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayerTimeDTO;
import salah.api.salaholm.dto.prayer.PrayersDTO;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Coordinates;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.entity.prayer.PrayerTime;
import salah.api.salaholm.entity.prayer.Prayer;

import java.util.List;

@Component
public class DTOMapper implements DTOMapperInterface {

    @Override
    @Cacheable(value = "locationCache", key = "#location.city.toLowerCase()")
    public LocationDTO toLocationDTO(Location location) {
        List<PrayersDTO> prayersDTOList = location.getPrayers()
                .stream()
                .map(this::toPrayersDTO)
                .toList();

        return LocationDTO.builder()
                .municipality(location.getMunicipality())
                .coordinates(toCoordinatesDTO(location.getCoordinates()))
                .prayers(prayersDTOList)
                .city(location.getCity())
                .build();
    }

    @Override
     public CoordinatesDTO toCoordinatesDTO(Coordinates coordinates) {
        return CoordinatesDTO.builder()
                .latitude(coordinates.getLatitude())
                .longitude(coordinates.getLongitude())
                .build();
    }

    @Override
    public PrayersDTO toPrayersDTO(Prayer prayer) {
        var prayerTimeDTOList = prayer.getPrayerTimes()
                .stream()
                .map(this::toPrayerTimeDTO)
                .toList();

        var prayerCalendarDTOList = prayer.getPrayerCalendars()
                .stream()
                .map(this::toPrayerCalendarDTO)
                .toList();

        return PrayersDTO.builder()
                .prayerTimes(prayerTimeDTOList)
                .prayerCalendars(prayerCalendarDTOList)
                .build();
    }

    @Override
    public PrayerTimeDTO toPrayerTimeDTO(PrayerTime prayerTime) {
        return PrayerTimeDTO.builder()
                .hour(prayerTime.getHour())
                .minute(prayerTime.getMinute())
                .prayerName(prayerTime.getPrayerName())
                .build();
    }

    @Override
    public PrayerCalendarDTO toPrayerCalendarDTO(PrayerCalendar prayerCalendar) {
        return PrayerCalendarDTO.builder()
                .date(prayerCalendar.getDate())
                .calendarType(prayerCalendar.getCalendarType())
                .month(prayerCalendar.getMonth())
                .year(prayerCalendar.getYear())
                .dayOfWeek(prayerCalendar.getDayOfWeek())
                .formattedCalendar(prayerCalendar.getFormattedCalendar())
                .important(prayerCalendar.isImportant())
                .build();
    }
}
