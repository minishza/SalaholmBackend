package salah.api.salaholm.dto.prayer;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;

import java.util.List;

@Data
@Builder
public class PrayersDTO {
    private List<PrayerCalendarDTO> prayerCalendars;

    private List<PrayerTimeDTO> prayerTimes;
}
