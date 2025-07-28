package salah.api.salaholm.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import salah.api.salaholm.service.LocationCacheService;
import salah.api.salaholm.util.Constants;

@Component
@RequiredArgsConstructor
public class Warmup {
    private final LocationCacheService locationCacheService;

    @EventListener(ApplicationReadyEvent.class)
    public void warmup() {
        Constants.POPULATED_CITIES.forEach(locationCacheService::getOrCacheLocationByCity);
    }
}
