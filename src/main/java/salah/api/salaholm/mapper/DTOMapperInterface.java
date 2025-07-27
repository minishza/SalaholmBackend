package salah.api.salaholm.mapper;

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

public interface DTOMapperInterface {
    LocationDTO toLocationDTO(Location location);
    PrayersDTO toPrayersDTO(Prayer prayers);
    PrayerTimeDTO toPrayerTimeDTO(PrayerTime prayerTime);
    PrayerCalendarDTO toPrayerCalendarDTO(PrayerCalendar prayerCalendar);
    CoordinatesDTO toCoordinatesDTO(Coordinates coordinates);
}
