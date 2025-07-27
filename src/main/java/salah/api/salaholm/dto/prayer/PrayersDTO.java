package salah.api.salaholm.dto.prayer;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.dto.calendar.PrayerCalendarDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class PrayersDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<PrayerCalendarDTO> prayerCalendars;

    private List<PrayerTimeDTO> prayerTimes;
}
