package salah.api.salaholm.entity.prayer;


import lombok.Builder;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import salah.api.salaholm.util.prayer.PrayerName;

import java.time.Duration;
import java.time.LocalTime;

@Builder
public class PrayerTime {

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField(index = true)
    private PrayerName prayerName;
    private String hour;
    private String minute;

    @QuerySqlField(index = true)
    private Long prayerId;

    public boolean isBefore(LocalTime time) {
        return toLocalTime().isBefore(time);
    }

    public boolean isAfter(LocalTime time) {
        return toLocalTime().isAfter(time);
    }

    public Duration timeUntil(LocalTime now) {
        return Duration.between(now, toLocalTime());
    }

    private LocalTime toLocalTime() {
        return LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
    }
}
