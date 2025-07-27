package salah.api.salaholm.dto.prayer;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.util.prayer.PrayerName;

@Data
@Builder
public class PrayerTimeDTO {
    private PrayerName prayerName;
    private String hour;
    private String minute;
}
