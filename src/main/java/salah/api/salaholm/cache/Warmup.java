package salah.api.salaholm.cache;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.location.Location;

@Component
public class Warmup implements CommandLineRunner {

    @Override
    @Async
    public void run(String... args) {


    }

    private Location warmupPrayers(String city) {
        return null;
    }
}
