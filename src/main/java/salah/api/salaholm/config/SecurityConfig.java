package salah.api.salaholm.config;

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
import salah.api.salaholm.mapper.PrayerMapper;
import salah.api.salaholm.util.RetryWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/prayer/**").permitAll()
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
        return new SimpleDateFormat("EEEE d MMMM y", Locale.ENGLISH);
    }

    @Bean
    public PrayerMapper prayerMapper(SimpleDateFormat dateFormatter) {
        return new PrayerMapper(dateFormatter);
    }

    @Bean
    public RetryWait retryWait(ChromeDriver driver) {
        return new RetryWait(driver);
    }
}
