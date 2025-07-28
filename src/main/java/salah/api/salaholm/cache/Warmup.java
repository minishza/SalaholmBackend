package salah.api.salaholm.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import salah.api.salaholm.util.Constants;

@Component
@RequiredArgsConstructor
public class Warmup implements CommandLineRunner {
    private final WarmUpService prayerService;

    @Override
    public void run(String... args) {
        Constants.POPULATED_CITIES.forEach(prayerService::warmupPrayers);
    }
}
