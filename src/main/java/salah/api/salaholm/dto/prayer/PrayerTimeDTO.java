package salah.api.salaholm.dto.prayer;

import lombok.Builder;
import lombok.Data;
import salah.api.salaholm.util.prayer.PrayerName;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class PrayerTimeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private PrayerName prayerName;
    private String hour;
    private String minute;
}
