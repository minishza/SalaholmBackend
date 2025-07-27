package salah.api.salaholm.mapper;

import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;
import salah.api.salaholm.dto.location.CoordinatesDTO;
import salah.api.salaholm.dto.location.LocationDTO;
import salah.api.salaholm.dto.prayer.PrayerDTO;
import salah.api.salaholm.dto.prayer.PrayerTimeDTO;
import salah.api.salaholm.entity.location.Location;

public class DTOMapper implements DTOMapperInterface {

    @Override
    public LocationDTO mapLocationToLocationDTO(Location location) {
        return null;
    }

    @Override
    public CoordinatesDTO mapLocationToCoordinatesDTO(Location location) {
        return null;
    }

    @Override
    public PrayerDTO mapLocationToPrayerDTO(Location location) {
        return null;
    }

    @Override
    public PrayerTimeDTO mapLocationToPrayerTimeDTO(Location location) {
        return null;
    }

    @Override
    public PrayerCalendarDTO mapLocationToPrayerCalendarDTO(Location location) {
        return null;
    }
}
