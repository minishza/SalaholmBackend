package salah.api.salaholm.config;

import org.springframework.stereotype.Component;

@Component
public final class Constants {
    private Constants() {}
    public static final String ISLAMISKA_URL = "https://www.islamiskaforbundet.se/bonetider/";
    public static final String ISLAMISKA_MONTH_DROPDOWN = "#ifis_bonetider_page_months option";
    public static final String ISLAMISKA_CITIES_DROPDOWN = "#ifis_bonetider_page_cities option";
}
