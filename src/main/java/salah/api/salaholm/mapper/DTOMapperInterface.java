package salah.api.salaholm.mapper;

import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;
import salah.api.salaholm.dto.location.CoordinatesDTO;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayerDTO;
import salah.api.salaholm.dto.prayer.PrayerTimeDTO;
import salah.api.salaholm.entity.location.Location;

public interface DTOMapperInterface {
    LocationDTO mapLocationToLocationDTO(Location location);
    CoordinatesDTO mapLocationToCoordinatesDTO(Location location);
    PrayerDTO mapLocationToPrayerDTO(Location location);
    PrayerTimeDTO mapLocationToPrayerTimeDTO(Location location);
    PrayerCalendarDTO mapLocationToPrayerCalendarDTO(Location location);
}
