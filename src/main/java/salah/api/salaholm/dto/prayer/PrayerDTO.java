package salah.api.salaholm.dto.prayer;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;
import salah.api.salaholm.entity.location.Location;

import java.util.List;

@Data
@Builder
public class PrayerDTO {

    private List<PrayerCalendarDTO> prayerCalendars;

    private List<PrayerTimeDTO> prayerTimes;

    private Location location;
}
