package salah.api.salaholm.config;

import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import salah.api.salaholm.entity.location.Coordinates;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.entity.prayer.Prayer;
import salah.api.salaholm.entity.prayer.PrayerTime;
import salah.api.salaholm.mapper.PrayerMapper;
import salah.api.salaholm.util.RetryWait;
import salah.api.salaholm.util.parser.LocationProvider;
import salah.api.salaholm.util.prayer.PrayerDateConverter;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Configuration
@EnableWebSecurity
@EnableIgniteRepositories
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/prayer/**").permitAll()
                        .requestMatchers( "/prayer/address").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public ChromeDriver chromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--disable-dev-shm-usage", "--no-sandbox", "start-maximized", "--disable-extensions", "--disable-css");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }

    @Bean
    public FluentWait<ChromeDriver> fluentWait(ChromeDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .ignoreAll(List.of(
                        StaleElementReferenceException.class,
                        NoSuchElementException.class)
                );
    }

    @Bean
    @Scope("prototype")
    public SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("EEEE, d, MMMM, y", Locale.ENGLISH);
    }

    @Bean
    public PrayerDateConverter prayerCalendar(SimpleDateFormat dateFormatter) {
        return new PrayerDateConverter(dateFormatter);
    }

    @Bean
    public PrayerMapper prayerMapper(PrayerDateConverter prayerDateConverter) {
        return new PrayerMapper(prayerDateConverter);
    }

    @Bean
    public LocationProvider locationProvider() {
        return new LocationProvider();
    }

    @Bean
    public RetryWait retryWait(ChromeDriver driver) {
        return new RetryWait(driver);
    }

    @Bean
    public IgniteConfiguration igniteCfg() {
        var prayerCache = new CacheConfiguration<>("prayers");
        prayerCache.setIndexedTypes(Long.class, Prayer.class);

        var coordinatesCache = new CacheConfiguration<>("coordinates");
        coordinatesCache.setIndexedTypes(UUID.class, Coordinates.class);

        var locationCache = new CacheConfiguration<>("location");
        locationCache.setIndexedTypes(Long.class, Location.class);

        var prayerTimeCache = new CacheConfiguration<>("prayerTime");
        prayerTimeCache.setIndexedTypes(Long.class, PrayerTime.class);

        var prayerCalendarCache = new CacheConfiguration<>("prayerCalendar");
        prayerCalendarCache.setIndexedTypes(UUID.class, Prayer.class);


        var igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setCacheConfiguration(prayerCache,
                coordinatesCache,
                locationCache,
                prayerTimeCache,
                prayerCalendarCache);

        return igniteConfiguration;
    }
}
