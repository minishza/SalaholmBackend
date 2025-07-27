package salah.api.salaholm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SalaholmApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalaholmApplication.class, args);
    }
}
