package salah.api.salaholm.mapper;

import org.springframework.stereotype.Component;
import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;
import salah.api.salaholm.dto.location.CoordinatesDTO;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayerTimeDTO;
import salah.api.salaholm.dto.prayer.PrayersDTO;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Coordinates;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.entity.prayer.PrayerTime;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapper implements DTOMapperInterface {

    @Override
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
    public PrayersDTO toPrayersDTO(Prayers prayer) {
        var prayerCalendarDTOList = prayer.getPrayerCalendars()
                .stream()
                .map(this::toPrayerCalendarDTO)
                .toList();

        var prayerTimeDTOList = prayer.getPrayerTimes()
                .stream()
                .map(this::toPrayerTimeDTO)
                .toList();

        return PrayersDTO.builder()
                .prayerCalendars(prayerCalendarDTOList)
                .prayerTimes(prayerTimeDTOList)
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
