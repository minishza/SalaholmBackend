package salah.api.salaholm.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static salah.api.salaholm.util.Constants.POPULATED_CITIES;

@Component
@RequiredArgsConstructor
public class Warmup implements CommandLineRunner {
    private final WarmUpService prayerService;

    @Override
    @Async
    public void run(String... args) {
        warmUpPrayers();
    }

    private void warmUpPrayers() {
        POPULATED_CITIES.forEach(prayerService::warmupPrayers);
    }
}
